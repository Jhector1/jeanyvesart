import {getElement} from "./helperMethod.js";
import {JsonRequest} from "./json_request.js";
import {checkCookie} from "./anonymous_user_cookie.js";
import {FormValidation} from "./formValidation.js";


class CookieManager {
    static cookieBasket = localStorage;

    constructor() {
    }

    static getCookieHeader() {
        let basket = new Set();
        for (let i = 0; i < this.cookieBasket.length; i++) {
            basket.add(this.cookieBasket.key(i));
        }
        return basket;
    }

    static async deleteUserItem(cookie) {
        try {
            return await fetch(`${apiBaseUrl}/${cookie.type}/artworks/delete-artwork/${cookie.id}`, {
                method: 'DELETE'
            });
        } catch (error) {

            console.log('Sorry an unexpected error occurred.' +
                'Failed to delete resource!' + error);
        }

    }


    static removeExpiredBuyerCartItem(cookie, date) {
        const deadline = 2592000000;
        if ((new Date(date).getTime() + 60000) < new Date().getTime()) {
            CookieManager.deleteUserItem(cookie);
        }
    }

    static async addCookie(cookie, quantity) {
        try {
            // console.log(quantity);
            const userId = "user12345";
            //const artwork = ElementManager.createArtWorkObject(cookieValue, cookie.type);


            const response = await JsonRequest.post(`${apiBaseUrl}/${cookie.type}/artworks/save`, {
                productId: cookie.id,
                quantity: quantity,
                customerId: checkCookie(userId),


            });

            if (!response.ok) {
                throw new Error("Sorry your artwork could not be save");

            }
            getElement(`.${cookie.type}-count`).innerHTML = Number(getElement(`.${cookie.type}-count`).innerHTML) + 1;

            console.log("artwork save successfully");

            console.log(response);

            return response;

        } catch (error) {
            console.log(error);
            alert(error)
        }

    }

    static async removeCookie(cookie) {

        try {
            const artworkId = cookie.id; // ID of the resource to be deleted
            const userId = checkCookie("user12345");
            const response = await fetch(`${apiBaseUrl}/${cookie.type}/artworks/${userId}/${artworkId}`, {
                method: 'DELETE'
            });


            getElement(`.${cookie.type}-count`).innerHTML = Number(getElement(`.${cookie.type}-count`).innerHTML) - 1;

            return response;
        } catch (error) {

            console.log('Sorry an unexpected error occurred.' +
                'Failed to delete resource!' + error);
            alert('Sorry an unexpected error occurred.' +
                'Failed to delete resource!' + error);
            // handle error
        }

    }

}

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

class CookieClient {
    constructor() {
        this.allCookies = CookieManager.getCookieHeader();
        this.clientBasket = new Set();
    }


    static displayDataLength(numAdd, data) {
        let sumLike = data.length;
        if (sumLike > 1) {
            console.log(sumLike);
            document.querySelector(numAdd).innerHTML = sumLike + " items";
        } else {
            document.querySelector(numAdd).innerHTML = sumLike + " item";

        }
    }

    displayNumberLike(numAdd, type) {
        fetch(`${apiBaseUrl}/${type}/artworks/${checkCookie("user12345")}`).then(response => {
            if (!response.ok) {
                throw new Error("Buyer cart is empty");
            } else {
                return response.json()
            }
        }).then(data => {
            let sumLike = data.length;
            if (sumLike >= 1) {
                document.querySelector(numAdd).innerHTML = sumLike;
            }
        }).catch((er) => {
            return er;
        });
    }


    addToClientBasket(char_type) {
        this.allCookies.forEach(value => {
            if (value.trim().charAt(0) === char_type) {
                this.clientBasket.add(value.trim());
            }
        });

    }

    async displayArtworkDataFromDB(parent_element, list, common_op, type) {
        try {
            const response = await fetch(`${apiBaseUrl}/${type}/artworks/${checkCookie("user12345")}`);
            const data = await response.json();

            if (window.location.pathname === "/cart") {
                getElement(`.${type}-component`).style.display = "block";
            }
            console.log(data);

            data.forEach(
                value => {
                    // CookieManager.removeExpiredBuyerCartItem(new Cookie(type, value.id), value.date);
                    const artwork_description = [];

                    artwork_description[0] = value.myProduct.imageUrl;

                    artwork_description[1] = value.myProduct.title;
                    artwork_description[2] = value.myProduct.medium;
                    artwork_description[3] = value.myProduct.size;
                    artwork_description[4] = value.myProduct.price;
                    const clone = document.querySelector(`${parent_element} ${list}`);
                    const cloneNode = clone.cloneNode(true);
                    let count = 1;
                    cloneNode.querySelector(`${list} img`).src = artwork_description[0];
                    const common = cloneNode.querySelectorAll(common_op);
                    cloneNode.querySelector(".inventory-data").value = value.inventory;

                    for (let y = 0; y < common.length; y++) {

                        common[y].innerHTML = artwork_description[count];
                        count++;
                    }
                    if (type === "cart" || window.location.pathname === "/wishlists") {
                        if (value.inventory <= 1 || window.location.pathname === "/cart") {
                            cloneNode.querySelector(".quantity-lbl").style.display = "none";
                        } else {
                            const quantityList = cloneNode.querySelector("#quantity").options;
                            quantityList[0].innerHTML = "";
                            quantityList[0].value = "";
                            //
                            for (let y = 1; y <= value.inventory; y++) {
                                const newOption = document.createElement("option");
                                cloneNode.querySelector("#quantity").appendChild(newOption);
                                quantityList[y].innerHTML = y.toString();
                                quantityList[y].value = y.toString();
                            }
                            cloneNode.querySelector(".quantity-lbl").style.display = "block";
                        }
                    }
                    if (type === "cart") {
                        cloneNode.querySelector(".quantity-cart").innerHTML = value.quantity;

                    }
                    cloneNode.style.display = "flex";
                    document.querySelector(parent_element).appendChild(cloneNode);
                });

            return data;

        } catch (e) {
            getElement(`.clear-${type}`).style.display = "none";
            getElement(`.parent-${type}-list`).style.margin = "auto";
            console.log("An error occurred: " + e)
            getElement(`.empty-${type}-alert`).style.display = "flex";
            console.log("error");

        }


    }


}

class ElementManager {
    constructor(element1, element2, elementSold) {
        this.quantityValue = 1;
        this.element1 = element1;
        this.elementSold = elementSold;
        this.element2 = element2;

    }


    checkVisibilityOrDisplay(operation, element) {
        operation === "display" ? element.style.display = "flex" : element.style.visibility = "visible";
    }


    async displayElementStatusFromDB(operation, cookie) {
        try {
            const response = await fetch(`${apiBaseUrl}/${cookie.type}/artworks/${checkCookie("user12345")}/${cookie.id}`);

            const data = await response.json();
            this.checkVisibilityOrDisplay(operation, this.element1);
            return data;
        } catch (er) {

            this.checkVisibilityOrDisplay(operation, this.element2);
            return er;
        }


    }

    static async displayElementStatusFromDB2(cookie) {
        try {
            const response = await fetch(`${apiBaseUrl}/${cookie.type}/artworks/${checkCookie("user12345")}`);

            //this.checkVisibilityOrDisplay(operation, this.element1);
            return response.json();
        } catch (er) {

            // this.checkVisibilityOrDisplay(operation, this.element2);
            return er;
        }


    }

    async purchaseAndCartItem(purchaseArray) {
        try {
            const response1 = fetch(`${apiBaseUrl}/${cookie.type}/artworks/${checkCookie("user12345")}`);
            console.log(response1); // .then(results => {


            const response = fetch(`${apiBaseUrl}/api/artworks/purchase-all`);

            const result = await Promise.allSettled([(await response1).json(), (await response).json()]);
            console.log(result);
            let mergeArrayIds;
            const [arr1, arr2] = result;
            console.log(arr1.value)
            if (arr1.status === 'fulfilled' && arr2.status === 'fulfilled') {
                mergeArrayIds = arr1.value.map(element => element.myProduct.id).concat(arr2.value);

            } else if (arr1.status === 'fulfilled') {
                mergeArrayIds = arr1.value.map(element => element.myProduct.id);
            } else if (arr2.status === 'fulfilled') {
                mergeArrayIds = arr2.value;
            } else mergeArrayIds = [];
            mergeArrayIds.sort((a, b) => a - b);
            purchaseArray = arr2;

            return mergeArrayIds;

        } catch (e) {
            return e;
        }
    }

    async displayElementStatusOrPurchase2(cookie, likedData, notLikedData, soldData, allData, operation) {
        console.log(mergeArrayIds);

        const formValidation = new FormValidation();
        let start = 0;
        let low = 0;

        for (let id of mergeArrayIds) {

            while (start < id) {
                console.log(start + "p0=");

                notLikedData[start].style.display = "block";

                start++;
                //   console.log(start)

            }
            start = id + 1;
            //console.log(id)
            // notLikedData[id].style.display = "none";
            if (arr2.value.includes(id)) {
                // console.log(id)
                likedData[id].style.display = "none";
                notLikedData[id].style.display = "none";

                soldData[id].style.display = "flex"

            } else {

                likedData[id].style.display = "flex";

            }


            while (low < id) {
                console.log(low)
                likedData[low].onclick = () => {
                    this.showElement2(new Cookie("favorite", low), operation, likedData[low], notLikedData[low]);
                }
                notLikedData[low].onclick = () => {
                    if (cookie.type === "cart" && allData.get("inventory").value > 1) {
                        this.quantityValue = allData.get("quantity").value;
                        console.log(allData.get("quantity") + "Yeah");
                        ElementManager.validateQuantity(formValidation, allData.get("quantity"), allData.get("quantity-error"));
                        if (formValidation.allinputValid) {

                            this.notShowElement2(new Cookie("favorite", low), operation, likedData[low], notLikedData[low]);
                        }
                    } else {
                        allData.set("quantity", 1);
                        this.notShowElement2(new Cookie("favorite", low), operation, likedData[low], notLikedData[low]);

                    }

                }

                low++;
                //   console.log(start)

            }
            low = id;
        }

        // for (let id in mergeArrayIds) {
        //     while (start < id) {
        //
        //
        //     }
        //     start = id+1;
        //     console.log(id)
        //     notLikedData[id].style.display = "none";
        //     if (arr2.value.includes(id)) {
        //         console.log(id)
        //         likedData[id].style.display = "none";
        //         soldData[id].style.display = "flex"
        //
        //     } else {
        //         likedData[id].style.display = "flex";
        //     }
        //
        // }


        return mergeArrayIds;


    }

    static binarySearch(arr, target) {
        let left = 0;
        let right = arr.length - 1;

        while (left <= right) {
            let mid = Math.floor((left + right) / 2);

            if (arr[mid] === target) {
                return mid; // Found the target, return its index
            } else if (arr[mid] < target) {
                left = mid + 1; // Target is in the right half
            } else {
                right = mid - 1; // Target is in the left half
            }
        }

        return -1; // Target not found
    }


    async displayElementStatusOrPurchase(cookie, operation, element4 = null) {
        try {
            const response = await fetch(`${apiBaseUrl}/api/artworks/purchase/${cookie.id}`);

            const purchaseArtwork = await response.json();
            this.element1.style.display = "none";
            this.element2.style.display = "none";
            if (window.location.pathname === "/wishlists" || window.location.pathname === "/cart")
                await CookieManager.removeCookie(cookie);
            if (element4 !== null) {
                element4.style.display = "none";
            } else {
                console.log("")
            }
            this.elementSold.style.display = "flex";

            return purchaseArtwork;

        } catch (error) {
            await this.displayElementStatusFromDB(operation, cookie)
        }

    }

    showElement(cookie, operation) {
        const temp = this.element1.innerHTML;
        let textM = "Loading...";
        if (cookie.type.toLowerCase().trim() === "cart") {
            textM = "Removing...";
        }
        this.element1.innerHTML = `<span class='spinner-grow spinner-grow-sm'></span> ${textM}`
        this.element1.disabled = true;


        setTimeout(() => {
            try {
                CookieManager.removeCookie(cookie).then(response => {
                    if (response.ok) {
                        if (operation === "display") {
                            this.element1.innerHTML = temp;
                            this.element1.style.display = "none";
                            this.element2.style.display = "flex";
                            this.element1.disabled = false;

                        } else if (operation === "visibility") {
                            this.element1.innerHTML = temp;
                            this.element1.disabled = false;

                            this.element1.style.visibility = "hidden";
                            this.element2.style.visibility = "visible";

                        }
                    }
                });
            } catch (err) {
                this.element1.innerHTML = "Remove From Cart"

                alert("Sorry an internal error occurs, Please try again " + err);
            }
        }, 1000)
    }

    showElement2(cookie, operation, element1, element2) {
        const temp = element1.innerHTML;
        let textM = "Loading...";
        if (cookie.type.toLowerCase().trim() === "cart") {
            textM = "Removing...";
        }
        element1.innerHTML = `<span class='spinner-grow spinner-grow-sm'></span> ${textM}`
        element1.disabled = true;


        setTimeout(() => {
            try {
                CookieManager.removeCookie(cookie).then(response => {
                    if (response.ok) {

                        if (operation === "display") {
                            element1.innerHTML = temp;
                            element1.style.display = "none";
                            element2.style.display = "flex";
                            element1.disabled = false;

                        } else if (operation === "visibility") {
                            element1.innerHTML = temp;
                            element1.disabled = false;

                            element1.style.visibility = "hidden";
                            element2.style.visibility = "visible";

                        }
                    }
                });
            } catch (err) {
                element1.innerHTML = "Remove From Cart"

                alert("Sorry an internal error occurs, Please try again " + err);
            }
        }, 1000)
    }

    notShowElement(allData, cookie, operation) {
        const temp = this.element2.innerHTML;
        let textM = "Loading...";
        if (cookie.type.toLowerCase().trim() === "cart") {
            textM = "Adding...";
        }
        this.element2.innerHTML = `<span class='spinner-grow spinner-grow-sm'></span> ${textM}`
        this.element2.disabled = true;

        setTimeout(() => {
            try {
                CookieManager.addCookie(cookie, this.quantityValue).then(response => {
                    if (response.ok) {
                        if (operation === "display") {
                            this.element1.style.display = "flex";
                            this.element2.style.display = "none";
                            this.element2.innerHTML = temp;
                            this.element2.disabled = false;


                        } else if (operation === "visibility") {
                            this.element1.style.visibility = "visible";
                            this.element2.style.visibility = "hidden";
                            this.element2.innerHTML = temp;
                            this.element2.disabled = false;

                        }


                    }
                });
            } catch (err) {
                this.element2.innerHTML = temp;

                alert("Sorry an internal error occurs, Please try again " + err);
            }
        }, 1000)

    }

    notShowElement2(cookie, operation, element1, element2) {
        const temp = element2.innerHTML;
        let textM = "Loading...";
        if (cookie.type.toLowerCase().trim() === "cart") {
            textM = "Adding...";
        }

        element2.innerHTML = `<span class='spinner-grow spinner-grow-sm'></span> ${textM}`
        element2.disabled = true;

        setTimeout(() => {
            try {
                CookieManager.addCookie(cookie, this.quantityValue).then(response => {
                    if (response.ok) {

                        if (operation === "display") {
                            element1.style.display = "flex";
                            element2.style.display = "none";
                            element2.innerHTML = temp;
                            element2.disabled = false;


                        } else if (operation === "visibility") {
                            element1.style.visibility = "visible";
                            element2.style.visibility = "hidden";
                            element2.innerHTML = temp;
                            element2.disabled = false;

                        }


                    }
                });
            } catch (err) {
                element2.innerHTML = temp;

                alert("Sorry an internal error occurs, Please try again " + err);
            }
        }, 1000)

    }


    static validateQuantity(formValidation, quantity, errorAlert) {
        formValidation.checkEmptyInput2(quantity, errorAlert);

    }

    switchAppearance(allData, cookie, operation, element4 = null) {
        this.displayElementStatusOrPurchase(cookie, operation, element4).then(data => data);
        const formValidation = new FormValidation();


        if (this.element1) {
            this.element1.onclick = () => {

                this.showElement(cookie, operation);
            }
        }
        if (this.element2) {
            this.element2.onclick = () => {
                if (cookie.type === "cart" && allData.get("inventory").value > 1) {
                    this.quantityValue = allData.get("quantity").value;
                    //console.log(allData.get("quantity") + "Yeah");
                    ElementManager.validateQuantity(formValidation, allData.get("quantity"), allData.get("quantity-error"));
                    if (formValidation.allinputValid) {

                        this.notShowElement(allData, cookie, operation);
                    }
                } else {
                    allData.set("quantity", 1);
                    this.notShowElement(allData, cookie, operation);

                }
            }
        }
    }

}

export {ElementManager, Cookie, CookieManager, CookieClient};