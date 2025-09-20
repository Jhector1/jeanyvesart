import { Cookie, CookieClient, CookieManager, ElementManager } from "./allcookies.js";
import { checkCookie } from "./anonymous_user_cookie.js";

/* ------------------------------- tiny helpers ------------------------------- */
function getAllElement(selector) {
    return document.querySelectorAll(selector);
}
function getElement(selector) {
    return document.querySelector(selector);
}

/* -------------------------------- slideshow idx ----------------------------- */
class SlideshowIndex {
    constructor(index) {
        this.index = index;
    }
    previous() {
        this.index -= 1;
    }
    next() {
        this.index += 1;
    }
    get elementIndex() {
        return this.index;
    }
}

/* ----------------------------- clear cookie basket -------------------------- */
async function clearCookieBasket(basketType, reload = true) {
    const userId = checkCookie("user12345");
    try {
        const res = await fetch(`${apiBaseUrl}/${basketType}/artworks/deleteAll/${userId}`, {
            method: "DELETE",
        });
        if (!res.ok) throw new Error("Failed to delete resource");
        if (reload) location.reload();
        else console.log("Deleted all resources successfully!");
    } catch (error) {
        alert(error);
    }
}

/* ----------------------- cart add/remove using ElementManager ---------------- */
function cartAddRemoveDesign(
    cookieNum,
    cart_remove,
    cart_add,
    all_artwork_data,
    elementSold,
    element4 = null,
    mode = "visibility"
) {
    const cookieForCart = new Cookie("cart", cookieNum);
    const elementManagerCart = new ElementManager(cart_remove, cart_add, elementSold);
    if (element4) elementManagerCart.switchAppearance(all_artwork_data, cookieForCart, mode, element4);
    else elementManagerCart.switchAppearance(all_artwork_data, cookieForCart, mode);
}

function cartAddRemoveDesignIcon(cookieNum, cart_remove, cart_add, all_artwork_data, elementSold) {
    const cookieForCart = new Cookie("cart", cookieNum);
    const elementManagerCart = new ElementManager(cart_remove, cart_add, elementSold);
    elementManagerCart.switchAppearance(all_artwork_data, cookieForCart, "display");
}

function likeOrNotDesign(cookieNum, not_liked_heart, liked_heart, all_artwork_data, elementSold) {
    const favoriteCookie = new Cookie("favorite", cookieNum);
    const elementManager = new ElementManager(liked_heart, not_liked_heart, elementSold);
    elementManager.switchAppearance(all_artwork_data, favoriteCookie, "display");
}

/* ----------------------------- remove item (list) --------------------------- */
/**
 * type: "cart" | "favorite"
 * item_class: selector for the remove buttons
 * data: server rows used to derive price/ids
 */
function removeItem(type, item_class, data) {
    // data.length may not match DOM if there is a hidden template — guard access.
    const allLikeItems = Array.from(getAllElement(item_class));
    // Heuristic: many lists include a template at [0]. Skip if hidden.
    const startIdx = allLikeItems[0] && allLikeItems[0].offsetParent === null ? 1 : 0;

    let remaining = data.length;
    for (let f = 0; f < data.length; f++) {
        const btn = allLikeItems[f + startIdx];
        if (!btn) continue;

        // Find the card container (3 levels up in original code). Guard each step.
        const cartItem =
            btn.parentNode?.parentNode?.parentNode ?? btn.closest?.(`.${type}-list`) ?? btn;

        btn.onclick = async () => {
            const price = Number(data[f]?.myProduct?.price ?? 0);
            const quantity = Number(data[f]?.quantity ?? 1);

            try {
                const res = await CookieManager.removeCookie(new Cookie(type, data[f].myProduct.id));
                if (!res?.ok) return;

                remaining = Math.max(0, remaining - 1);
                const totalItemsEl = getElement(`.${type}-total-items`);
                if (totalItemsEl) {
                    totalItemsEl.textContent = `${remaining} ${remaining === 1 ? "item" : "items"}`;
                }

                if (window.location.pathname === "/cart") {
                    // Update total from DOM safely
                    const totalEl = getElement(".item-total");
                    if (totalEl) {
                        // Prefer recompute for correctness
                        totalItemsPrice();
                    }
                }

                cartItem?.remove?.();

                // If no more items (apart from a template), reload to show empty state
                const realItemsLeft = Array.from(getAllElement(`.${type}-list`)).filter(
                    (n) => n.offsetParent !== null
                ).length;
                if (realItemsLeft <= 1) {
                    location.reload();
                }
            } catch (e) {
                console.error(e);
            }
        };
    }
}

/* ----------------------------- toggle by checkbox --------------------------- */
function offerDecisionResponse(element, elementSelector) {
    const elements = getAllElement(elementSelector);
    element.onclick = () => {
        const show = !!element.checked;
        elements.forEach((el) => {
            if (el) el.style.display = show ? "block" : "none";
        });
    };
}

/* ------------------------------ totals (cart) -------------------------------- */
function totalItemsPrice() {
    const prices = getAllElement(".item-price");
    let total = 0;
    // If first is a template with price label, start from index 1
    const startIdx = prices[0] && prices[0].offsetParent === null ? 1 : 1; // keep your original behavior
    for (let j = startIdx; j < prices.length; j++) {
        const raw = prices[j]?.textContent ?? prices[j]?.innerHTML ?? "0";
        const val = Number(String(raw).replace(/[^0-9.]+/g, ""));
        if (!Number.isNaN(val)) total += val;
    }
    const totalEl = getElement(".item-total");
    if (totalEl) totalEl.textContent = `$${total.toFixed(2).replace(/\.00$/, "")}`;
}

/* ------------------------------ visit artwork -------------------------------- */
export function visitArtwork(imgElement, artworkId) {
    if (!imgElement) return;
    imgElement.addEventListener(
        "click",
        () => {
            window.location.href = "/artworks/00000" + artworkId;
        },
        { passive: true }
    );
}

/* --------------------------- horizontal slider nav --------------------------- */
function slideNextPrevious() {
    const nextBtns = getAllElement(".parent-next2");
    const prevBtns = getAllElement(".parent-previous2");
    const carousels = getAllElement(".allArtParent");

    const updateButtons = (idx) => {
        const el = carousels[idx];
        const next = nextBtns[idx];
        const prev = prevBtns[idx];
        if (!el || !next || !prev) return;

        const atStart = el.scrollLeft <= 0;
        const atEnd = Math.ceil(el.scrollLeft + el.clientWidth) >= el.scrollWidth;

        prev.style.visibility = atStart ? "hidden" : "visible";
        next.style.visibility = atEnd ? "hidden" : "visible";
    };

    for (let k = 0; k < carousels.length; k++) {
        const el = carousels[k];
        const next = nextBtns[k];
        const prev = prevBtns[k];
        if (!el || !next || !prev) continue;

        // Initial state
        updateButtons(k);

        // Smooth scroll
        next.onclick = () => {
            el.scrollBy({ left: 600, behavior: "smooth" });
        };
        prev.onclick = () => {
            el.scrollBy({ left: -600, behavior: "smooth" });
        };

        // Update on scroll
        el.addEventListener(
            "scroll",
            () => {
                updateButtons(k);
            },
            { passive: true }
        );

        // Also update after a small delay to catch momentum scroll
        el.addEventListener(
            "wheel",
            () => {
                setTimeout(() => updateButtons(k), 150);
            },
            { passive: true }
        );
    }
}

/* ------------------------------ message dialog ------------------------------- */
function toggleCloseIcon(displayerSelector) {
    const opener = getElement(displayerSelector);
    const dlg = getElement(".message-contact");
    const cancel = getElement("#message-cancel-button");
    if (!opener || !dlg || !cancel) return;

    opener.onclick = () => (dlg.style.display = "flex");
    cancel.onclick = () => (dlg.style.display = "none");
}

/* ------------------------------- unload alert -------------------------------- */
function onReloadAlert(reload = true) {
    if (reload === true) {
        window.onbeforeunload = function () {
            return "Are you sure you want to leave? Some data might be lost!";
        };
    } else {
        window.onbeforeunload = null;
    }
}

/* ---------------------- wire click to open product page ---------------------- */
export function artwork_template_visitor_manager() {
    const frames = getAllElement(".frame");
    const ids = getAllElement(".image-id");
    if (frames.length === 0) {
        const parent = getElement(".allArtParent")?.parentNode;
        if (parent && parent.style) parent.style.display = "none";
        return;
    }
    for (let i = 0; i < frames.length; i++) {
        const img = frames[i].querySelector(".artwork-data-img");
        const id = ids[i]?.value;
        if (img && id) visitArtwork(img, id);
    }
}

/* ---------------------------------- exports ---------------------------------- */
export {
    cartAddRemoveDesignIcon,
    onReloadAlert,
    toggleCloseIcon,
    totalItemsPrice,
    slideNextPrevious,
    likeOrNotDesign,
    offerDecisionResponse,
    SlideshowIndex,
    removeItem,
    cartAddRemoveDesign,
    clearCookieBasket,
    getAllElement,
    getElement,
};
