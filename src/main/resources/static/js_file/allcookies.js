import { getElement } from "./helperMethod.js";
import { JsonRequest } from "./json_request.js";
import { checkCookie } from "./anonymous_user_cookie.js";
import { FormValidation } from "./formValidation.js";

/* -------------------------- small shared helpers -------------------------- */
const getUserId = () => checkCookie("user12345");

async function safeFetch(url, init) {
    const res = await fetch(url, init);
    return res;
}

async function safeFetchJson(url, init) {
    const res = await safeFetch(url, init);
    if (!res.ok) throw new Error(`HTTP ${res.status} for ${url}`);
    return res.json();
}

/* --------------------------------- CookieManager --------------------------------- */
class CookieManager {
    static cookieBasket = localStorage;

    static getCookieHeader() {
        const basket = new Set();
        for (let i = 0; i < this.cookieBasket.length; i++) {
            basket.add(this.cookieBasket.key(i));
        }
        return basket;
    }

    static async deleteUserItem(cookie) {
        try {
            return await safeFetch(
                `${apiBaseUrl}/${cookie.type}/artworks/delete-artwork/${cookie.id}`,
                { method: "DELETE" }
            );
        } catch (error) {
            console.log(
                "Sorry an unexpected error occurred. Failed to delete resource! " + error
            );
        }
    }

    // Remove items older than 30 days (2592000000 ms)
    static removeExpiredBuyerCartItem(cookie, date) {
        const deadline = 2592000000; // 30 days
        if (new Date(date).getTime() + deadline < Date.now()) {
            CookieManager.deleteUserItem(cookie);
        }
    }

    static async addCookie(cookie, quantity) {
        try {
            const response = await JsonRequest.post(
                `${apiBaseUrl}/${cookie.type}/artworks/save`,
                {
                    productId: cookie.id,
                    quantity: quantity,
                    customerId: getUserId(),
                }
            );

            if (!response?.ok) {
                throw new Error("Sorry your artwork could not be saved");
            }

            const counterEl = getElement(`.${cookie.type}-count`);
            if (counterEl) {
                counterEl.innerHTML = String(Number(counterEl.innerHTML || "0") + 1);
            }

            return response;
        } catch (error) {
            console.log(error);
            alert(error);
        }
    }

    static async removeCookie(cookie) {
        try {
            const res = await safeFetch(
                `${apiBaseUrl}/${cookie.type}/artworks/${getUserId()}/${cookie.id}`,
                { method: "DELETE" }
            );

            if (res.ok) {
                const counterEl = getElement(`.${cookie.type}-count`);
                if (counterEl) {
                    counterEl.innerHTML = String(
                        Math.max(0, Number(counterEl.innerHTML || "0") - 1)
                    );
                }
            }

            return res;
        } catch (error) {
            console.log(
                "Sorry an unexpected error occurred. Failed to delete resource! " +
                error
            );
            alert(
                "Sorry an unexpected error occurred. Failed to delete resource! " + error
            );
        }
    }
}

/* --------------------------------- Cookie --------------------------------- */
class Cookie {
    constructor(type, id) {
        this.cookieType = type;
        this.cookieID = id;
    }
    get id() {
        return this.cookieID;
    }
    get type() {
        return this.cookieType;
    }
}

/* --------------------------------- CookieClient --------------------------------- */
class CookieClient {
    constructor() {
        this.allCookies = CookieManager.getCookieHeader();
        this.clientBasket = new Set();
    }

    static displayDataLength(numAdd, data) {
        const sum = data.length;
        const el = document.querySelector(numAdd);
        if (el) el.textContent = sum + (sum === 1 ? " item" : " items");
    }

    displayNumberLike(numAdd, type) {
        safeFetchJson(`${apiBaseUrl}/${type}/artworks/${getUserId()}`)
            .then((data) => {
                const sum = data.length;
                if (sum >= 1) {
                    const el = document.querySelector(numAdd);
                    if (el) el.textContent = String(sum);
                }
            })
            .catch(() => {});
    }

    addToClientBasket(char_type) {
        this.allCookies.forEach((value) => {
            if (value.trim().charAt(0) === char_type) {
                this.clientBasket.add(value.trim());
            }
        });
    }

    async displayArtworkDataFromDB(parent_element, list, common_op, type) {
        try {
            const data = await safeFetchJson(
                `${apiBaseUrl}/${type}/artworks/${getUserId()}`
            );

            if (window.location.pathname === "/cart") {
                const comp = getElement(`.${type}-component`);
                if (comp) comp.style.display = "block";
            }

            const parent = document.querySelector(parent_element);
            if (!parent) return data;

            const templateEl = document.querySelector(`${parent_element} ${list}`);
            if (!templateEl) return data;

            const frag = document.createDocumentFragment();

            data.forEach((value) => {
                const p = value.myProduct;

                const cloneNode = templateEl.cloneNode(true);
                // image
                const imgEl = cloneNode.querySelector(`${list} img`);
                if (imgEl) imgEl.src = (p.imageUrl || "").trim();

                // inventory/quantity
                const invInput = cloneNode.querySelector(".inventory-data");
                if (invInput) invInput.value = value.inventory;

                // common text fields
                const common = cloneNode.querySelectorAll(common_op);
                const textVals = [p.title, p.medium, p.size, p.price];
                for (let i = 0; i < common.length && i < textVals.length; i++) {
                    common[i].textContent = textVals[i];
                }

                // quantity selector
                if (type === "cart" || window.location.pathname === "/wishlists") {
                    const qtyLbl = cloneNode.querySelector(".quantity-lbl");
                    const qtySel = cloneNode.querySelector("#quantity");

                    if (value.inventory <= 1 || window.location.pathname === "/cart") {
                        if (qtyLbl) qtyLbl.style.display = "none";
                    } else if (qtySel) {
                        // reset then fill options
                        qtySel.innerHTML = "";
                        const empty = document.createElement("option");
                        empty.value = "";
                        empty.textContent = "";
                        qtySel.appendChild(empty);

                        for (let i = 1; i <= value.inventory; i++) {
                            const opt = document.createElement("option");
                            opt.value = String(i);
                            opt.textContent = String(i);
                            qtySel.appendChild(opt);
                        }
                        if (qtyLbl) qtyLbl.style.display = "block";
                    }
                }

                if (type === "cart") {
                    const q = cloneNode.querySelector(".quantity-cart");
                    if (q) q.textContent = value.quantity;
                }

                cloneNode.style.display = "flex";
                frag.appendChild(cloneNode);
            });

            parent.appendChild(frag);
            return data;
        } catch (e) {
            const clear = getElement(`.clear-${type}`);
            const parentList = getElement(`.parent-${type}-list`);
            const emptyAlert = getElement(`.empty-${type}-alert`);

            if (clear) clear.style.display = "none";
            if (parentList) parentList.style.margin = "auto";
            if (emptyAlert) emptyAlert.style.display = "flex";
            console.log("error");
        }
    }
}

/* --------------------------------- ElementManager --------------------------------- */
class ElementManager {
    constructor(element1, element2, elementSold) {
        this.quantityValue = 1;
        this.element1 = element1; // “Remove / Liked” button
        this.element2 = element2; // “Add / Not Liked” button
        this.elementSold = elementSold;
    }

    checkVisibilityOrDisplay(operation, element) {
        operation === "display"
            ? (element.style.display = "flex")
            : (element.style.visibility = "visible");
    }

    async displayElementStatusFromDB(operation, cookie) {
        try {
            await safeFetchJson(
                `${apiBaseUrl}/${cookie.type}/artworks/${getUserId()}/${cookie.id}`
            );
            this.checkVisibilityOrDisplay(operation, this.element1);
        } catch {
            this.checkVisibilityOrDisplay(operation, this.element2);
        }
    }

    static async displayElementStatusFromDB2(cookie) {
        try {
            return await safeFetchJson(
                `${apiBaseUrl}/${cookie.type}/artworks/${getUserId()}`
            );
        } catch (er) {
            return er;
        }
    }

    /**
     * Fetches:
     *  - All item IDs for this user/type
     *  - All purchased IDs (global endpoint)
     * Returns a merged sorted list and a Set of purchased IDs.
     */
    async purchaseAndCartItem(type) {
        try {
            const [userRes, purchasedRes] = await Promise.allSettled([
                safeFetchJson(`${apiBaseUrl}/${type}/artworks/${getUserId()}`),
                safeFetchJson(`${apiBaseUrl}/api/artworks/purchase-all`),
            ]);

            const userItems =
                userRes.status === "fulfilled" ? userRes.value : /* [] */ [];
            const purchasedIdsArr =
                purchasedRes.status === "fulfilled" ? purchasedRes.value : /* [] */ [];

            const userIds = Array.isArray(userItems)
                ? userItems.map((el) => Number(el?.myProduct?.id)).filter(Number.isFinite)
                : [];

            const purchasedIds = Array.isArray(purchasedIdsArr)
                ? purchasedIdsArr.map((n) => Number(n)).filter(Number.isFinite)
                : [];

            const mergedIds = Array.from(new Set([...userIds, ...purchasedIds])).sort(
                (a, b) => a - b
            );
            return { mergedIds, purchasedIds: new Set(purchasedIds) };
        } catch (e) {
            return { mergedIds: [], purchasedIds: new Set() };
        }
    }

    /**
     * Batch set UI based on purchase/cart state.
     * Uses hidden inputs .image-id next to frames to map DOM index -> productId.
     */
    async displayElementStatusOrPurchase2(
        cookie,
        likedData,
        notLikedData,
        soldData,
        allData,
        operation
    ) {
        const { mergedIds, purchasedIds } = await this.purchaseAndCartItem(
            cookie.type
        );

        // Map DOM -> productId using .image-id elements in order
        const idEls = Array.from(document.querySelectorAll(".image-id"));
        const ids = idEls.map((el) => Number(el.value));

        for (let i = 0; i < ids.length; i++) {
            const pid = ids[i];

            // default hide
            if (likedData[i]) likedData[i].style.display = "none";
            if (notLikedData[i]) notLikedData[i].style.display = "none";
            if (soldData[i]) soldData[i].style.display = "none";

            if (!Number.isFinite(pid)) continue;

            if (purchasedIds.has(pid)) {
                if (soldData[i]) soldData[i].style.display = "flex";
                continue;
            }

            if (mergedIds.includes(pid)) {
                // Present in user's list (e.g., wish/cart) → show liked
                if (likedData[i]) likedData[i].style.display = "flex";
            } else {
                if (notLikedData[i]) notLikedData[i].style.display = "flex";
            }

            // Attach handlers once
            if (likedData[i] && !likedData[i]._bound) {
                likedData[i]._bound = true;
                likedData[i].onclick = () =>
                    this.showElement2(
                        new Cookie(cookie.type === "cart" ? "cart" : "favorite", pid),
                        operation,
                        likedData[i],
                        notLikedData[i]
                    );
            }
            if (notLikedData[i] && !notLikedData[i]._bound) {
                notLikedData[i]._bound = true;
                notLikedData[i].onclick = () => {
                    // quantity if available
                    const formValidation = new FormValidation();
                    let qty = 1;

                    const invEl = allData?.get?.("inventory");
                    const qEl = allData?.get?.("quantity");
                    const qErr = allData?.get?.("quantity-error");

                    if (cookie.type === "cart" && invEl && Number(invEl.value) > 1 && qEl) {
                        ElementManager.validateQuantity(formValidation, qEl, qErr);
                        if (!formValidation.allinputValid) return;
                        qty = Number(qEl.value || 1);
                    }

                    this.quantityValue = qty;
                    this.notShowElement2(
                        new Cookie(cookie.type === "cart" ? "cart" : "favorite", pid),
                        operation,
                        likedData[i],
                        notLikedData[i]
                    );
                };
            }
        }

        return mergedIds;
    }

    static binarySearch(arr, target) {
        let left = 0,
            right = arr.length - 1;
        while (left <= right) {
            const mid = (left + right) >> 1;
            if (arr[mid] === target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    async displayElementStatusOrPurchase(cookie, operation, element4 = null) {
        try {
            await safeFetchJson(`${apiBaseUrl}/api/artworks/purchase/${cookie.id}`);

            this.element1.style.display = "none";
            this.element2.style.display = "none";
            if (window.location.pathname === "/wishlists" || window.location.pathname === "/cart") {
                await CookieManager.removeCookie(cookie);
            }
            if (element4) element4.style.display = "none";

            this.elementSold.style.display = "flex";
            return true;
        } catch {
            await this.displayElementStatusFromDB(operation, cookie);
            return false;
        }
    }

    showElement(cookie, operation) {
        const temp = this.element1.innerHTML;
        const textM = cookie.type.toLowerCase().trim() === "cart" ? "Removing..." : "Loading...";
        this.element1.innerHTML = `<span class='spinner-grow spinner-grow-sm'></span> ${textM}`;
        this.element1.disabled = true;

        CookieManager.removeCookie(cookie)
            .then((response) => {
                if (response?.ok) {
                    if (operation === "display") {
                        this.element1.innerHTML = temp;
                        this.element1.style.display = "none";
                        this.element2.style.display = "flex";
                        this.element1.disabled = false;
                    } else {
                        this.element1.innerHTML = temp;
                        this.element1.disabled = false;
                        this.element1.style.visibility = "hidden";
                        this.element2.style.visibility = "visible";
                    }
                } else {
                    this.element1.innerHTML = temp;
                    this.element1.disabled = false;
                }
            })
            .catch((err) => {
                this.element1.innerHTML = temp;
                this.element1.disabled = false;
                alert("Sorry an internal error occurred. Please try again. " + err);
            });
    }

    showElement2(cookie, operation, element1, element2) {
        const temp = element1.innerHTML;
        const textM = cookie.type.toLowerCase().trim() === "cart" ? "Removing..." : "Loading...";
        element1.innerHTML = `<span class='spinner-grow spinner-grow-sm'></span> ${textM}`;
        element1.disabled = true;

        CookieManager.removeCookie(cookie)
            .then((response) => {
                if (response?.ok) {
                    if (operation === "display") {
                        element1.innerHTML = temp;
                        element1.style.display = "none";
                        element2.style.display = "flex";
                        element1.disabled = false;
                    } else {
                        element1.innerHTML = temp;
                        element1.disabled = false;
                        element1.style.visibility = "hidden";
                        element2.style.visibility = "visible";
                    }
                } else {
                    element1.innerHTML = temp;
                    element1.disabled = false;
                }
            })
            .catch((err) => {
                element1.innerHTML = temp;
                element1.disabled = false;
                alert("Sorry an internal error occurred. Please try again. " + err);
            });
    }

    notShowElement(allData, cookie, operation) {
        const temp = this.element2.innerHTML;
        const textM = cookie.type.toLowerCase().trim() === "cart" ? "Adding..." : "Loading...";
        this.element2.innerHTML = `<span class='spinner-grow spinner-grow-sm'></span> ${textM}`;
        this.element2.disabled = true;

        CookieManager.addCookie(cookie, this.quantityValue)
            .then((response) => {
                if (response?.ok) {
                    if (operation === "display") {
                        this.element1.style.display = "flex";
                        this.element2.style.display = "none";
                        this.element2.innerHTML = temp;
                        this.element2.disabled = false;
                    } else {
                        this.element1.style.visibility = "visible";
                        this.element2.style.visibility = "hidden";
                        this.element2.innerHTML = temp;
                        this.element2.disabled = false;
                    }
                } else {
                    this.element2.innerHTML = temp;
                    this.element2.disabled = false;
                }
            })
            .catch((err) => {
                this.element2.innerHTML = temp;
                this.element2.disabled = false;
                alert("Sorry an internal error occurred. Please try again. " + err);
            });
    }

    notShowElement2(cookie, operation, element1, element2) {
        const temp = element2.innerHTML;
        const textM = cookie.type.toLowerCase().trim() === "cart" ? "Adding..." : "Loading...";
        element2.innerHTML = `<span class='spinner-grow spinner-grow-sm'></span> ${textM}`;
        element2.disabled = true;

        CookieManager.addCookie(cookie, this.quantityValue)
            .then((response) => {
                if (response?.ok) {
                    if (operation === "display") {
                        element1.style.display = "flex";
                        element2.style.display = "none";
                        element2.innerHTML = temp;
                        element2.disabled = false;
                    } else {
                        element1.style.visibility = "visible";
                        element2.style.visibility = "hidden";
                        element2.innerHTML = temp;
                        element2.disabled = false;
                    }
                } else {
                    element2.innerHTML = temp;
                    element2.disabled = false;
                }
            })
            .catch((err) => {
                element2.innerHTML = temp;
                element2.disabled = false;
                alert("Sorry an internal error occurred. Please try again. " + err);
            });
    }

    static validateQuantity(formValidation, quantity, errorAlert) {
        formValidation.checkEmptyInput2(quantity, errorAlert);
    }

    switchAppearance(allData, cookie, operation, element4 = null) {
        this.displayElementStatusOrPurchase(cookie, operation, element4);

        const formValidation = new FormValidation();

        this.element1.onclick = () => {
            this.showElement(cookie, operation);
        };

        this.element2.onclick = () => {
            if (cookie.type === "cart" && allData.get("inventory").value > 1) {
                this.quantityValue = allData.get("quantity").value;
                ElementManager.validateQuantity(
                    formValidation,
                    allData.get("quantity"),
                    allData.get("quantity-error")
                );
                if (formValidation.allinputValid) {
                    this.notShowElement(allData, cookie, operation);
                }
            } else {
                allData.set("quantity", 1);
                this.notShowElement(allData, cookie, operation);
            }
        };
    }
}

export { ElementManager, Cookie, CookieManager, CookieClient };
