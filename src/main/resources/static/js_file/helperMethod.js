import {Cookie, CookieClient, CookieManager, ElementManager} from "./allcookies.js";
import {checkCookie} from "./anonymous_user_cookie.js";

function getAllElement(selector) {
    return document.querySelectorAll(selector);
}

function getElement(selector) {
    return document.querySelector(selector);
}



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

function clearCookieBasket(basketType, reload = true) {
    const userId = checkCookie("user12345");

    fetch(`http://localhost:9090/${basketType}/artworks/deleteAll/${userId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                console.log('Failed to delete resource');
                throw new Error('Failed to delete resource');
            } else {
                if (reload === true) {
                    location.reload();
                }
                console.log('delete resource successfully!');

            }
        })
        .catch(error => {
            alert(error);
            // handle error
        });


}

function cartAddRemoveDesign(cookieNum, cart_remove, cart_add, img_url, all_artwork_data, elementSold, element4 = null, mode='visibility') {
    const cookieForCart = new Cookie("cart", cookieNum);

    const elementManagerCart = new ElementManager(cart_remove, cart_add, elementSold);

    if (element4 !== null) elementManagerCart.switchAppearance(img_url, all_artwork_data, cookieForCart, mode, element4);
    else elementManagerCart.switchAppearance(img_url, all_artwork_data, cookieForCart, mode );

}

function cartAddRemoveDesignIcon(cookieNum, cart_remove, cart_add, img_url, all_artwork_data, elementSold) {
    const cookieForCart = new Cookie("cart", cookieNum);

    const elementManagerCart = new ElementManager(cart_remove, cart_add, elementSold);
    elementManagerCart.switchAppearance(img_url, all_artwork_data, cookieForCart, "display");

}

function likeOrNotDesign(cookieNum, not_liked_heart, liked_heart, img_url, all_artwork_data, elementSold) {
    const favoriteCookie = new Cookie("favorite", cookieNum);

    const elementManager = new ElementManager(liked_heart, not_liked_heart, elementSold);

    elementManager.switchAppearance(img_url, all_artwork_data, favoriteCookie, "display");

}


function removeItem(type, item_class, data) {
    let numberOfItem = data.length-1;
    const allLikeItems = getAllElement(item_class);
    for (let f = 0; f < data.length; f++) {
        const cartItem = allLikeItems[f+1].parentNode.parentNode.parentNode;
        allLikeItems[f + 1].onclick = () => {
            const price = data[f].myProduct.price;
            const quantity2 = data[f].quantity;

            CookieManager.removeCookie(new Cookie(type, data[f].myProduct.id)).then(response=>{
                if(response.ok){
                    if(numberOfItem>1) {
                        getElement(`.${type}-total-items`).innerHTML = numberOfItem-- + " items";
                    }else{
                        getElement(`.${type}-total-items`).innerHTML = numberOfItem-- + " item";

                    } console.log(numberOfItem);
                    if(window.location.pathname==="/cart"){
                        const total_item = getElement(".item-total");
                        const total_price1 = Number(total_item.innerHTML.substring(1))-(Number(quantity2)*Number(price));
                        total_item.innerHTML = `$${total_price1}`;

                    }
                    //CookieClient.displayDataLength(`.${type}-total-items`, data);


                    cartItem.remove();
                    if (getAllElement(`.${type}-list`).length<=1){
                        location.reload();
                    }

                }
            });

        }
    }
}



function offerDecisionResponse(element, elementSelector) {
    const elementOfSelector = getAllElement(elementSelector);
    element.onclick = () => {
        if (element.checked) {

            elementOfSelector.forEach(value => {
                value.style.display = "block";
            })
        } else {
            elementOfSelector.forEach(value => {
                value.style.display = "none";
            })

        }
    }
}

function totalItemsPrice() {

    const total_items_price = getAllElement(".item-price");
    let total_price = 0;
    for (let j = 1; j < total_items_price.length; j++) {
        total_price += Number(total_items_price[j].innerHTML);
    }
    const total_item = getElement(".item-total");
    total_item.innerHTML = `$${total_price}`;
}

export function visitArtwork(imgElement, artworkId){
    imgElement.onclick = () => {
        window.location.href = "/artworks/00000" + artworkId;
    }
}

function slideNextPrevious() {
    const next2 = getAllElement(".parent-next2");
    const previous2 = getAllElement(".parent-previous2");
    const allArtParent = getAllElement(".allArtParent");
    for (let k = 0; k < next2.length; k++) {
        next2[k].onclick = () => {
            previous2[k].style.visibility = "visible";

            if (allArtParent[k].scrollLeft === allArtParent[k].scrollWidth - allArtParent[k].clientWidth) {
                next2[k].style.visibility = "hidden";

                console.log("yeah")
            }
            allArtParent[k].scrollBy(600, 0);
        }


        previous2[k].onclick = () => {
            next2[k].style.visibility = "visible";
            if (allArtParent[k].scrollLeft === 0) {
                previous2[k].style.visibility = "hidden";
            }
            allArtParent[k].scrollBy(-600, 0);
        }
    }
}



function toggleCloseIcon(displayerSelector) {
    getElement(displayerSelector).onclick = () => document.querySelector(".message-contact").style.display = "flex";
    getElement("#message-cancel-button").onclick = () => document.querySelector(".message-contact").style.display = "none";

}

function onReloadAlert(reload = true) {
    if (reload === true) {
        window.onbeforeunload = function () {
            return "Are you sure you want to leave? Some data might be lost!";
        }
    }
}
export function artwork_template_visitor_manager(){
    const all_frame = getAllElement(".frame");

    const cookieNum = getAllElement(".image-id");
    if(all_frame.length===0){
        getElement(".allArtParent").parentNode.style.display="none";
    }
    for (let i = 0; i < all_frame.length; i++) {
        const img_url = all_frame[i].querySelector(".artwork-data-img");


        visitArtwork(img_url, cookieNum[i].value);

    }
}
export {
    cartAddRemoveDesignIcon, onReloadAlert, toggleCloseIcon, totalItemsPrice,

    slideNextPrevious,

    likeOrNotDesign,
    offerDecisionResponse,
    SlideshowIndex,
    removeItem,
    cartAddRemoveDesign,
    clearCookieBasket,
    getAllElement,
    getElement
};
