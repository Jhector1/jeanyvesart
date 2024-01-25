import {
    cartAddRemoveDesign, cartAddRemoveDesignIcon,
    getAllElement,
    getElement,
    likeOrNotDesign,
    slideNextPrevious,
    SlideshowIndex, visitArtwork
} from "./helperMethod.js";


const all_frame = getAllElement(".frame");

const cookieNum = getAllElement(".image-id");
const liked_heart = getAllElement(".liked-icon");
const not_liked_heart = getAllElement(".not-liked-icon");

// const cart_add = getAllElement(".cart-add-action");
// const cart_remove = getAllElement(".cart-remove-action");
const elementSold = getAllElement(".sold-item");
//
// const cart_icon_add = getAllElement(".cart-add-box");
// const cart_icon_remove = getAllElement(".cart-remove-box");
//
// const popup_cart_add = getElement(".popup-cart-add-action");
// const popup_cart_remove = getElement(".popup-cart-remove-action");
// const popup_liked_heart = getElement(".popup-liked-heart");
// const popup_not_liked_heart = getElement(".popup-not-liked-heart");
// const popup_element_sold = getElement(".popup-sold-item");
// const popup_purchase = getElement(".popup-purchase");
// const unique = getAllElement(".uniqueness");
const artworkID = getAllElement(".image-id");
for (let i = 0; i < all_frame.length; i++) {
    const all_artwork = all_frame[i].querySelectorAll(".artwork-data");
    const img_url = all_frame[i].querySelector(".artwork-data-img");

    const all_artwork_data = new Map();
    all_artwork_data.set("id", artworkID[i].value);
    all_artwork_data.set("date", img_url.src);

    all_artwork_data.set("imageUrl", img_url.src);
    all_artwork_data.set("title", all_artwork[0].innerHTML);
    all_artwork_data.set("medium", all_artwork[1].innerHTML);
    all_artwork_data.set("size", all_artwork[2].innerHTML);
    all_artwork_data.set("price", all_artwork[3].innerHTML);
    //all_artwork_data.set("unique", unique[i].value);
    //all_artwork_data.set("inventory", getAllElement(".inventory-data")[i]);
    all_artwork_data.set("description", getAllElement(".describe-artwork")[i].innerHTML);

    (() => {
        likeOrNotDesign(cookieNum[i].value, not_liked_heart[i], liked_heart[i], img_url.src, all_artwork_data, elementSold[i]);
    })();

    (() => {
        // cartAddRemoveDesign(cookieNum[i].innerHTML, cart_remove[i], cart_add[i], img_url.src, all_artwork_data, elementSold[i]);

    })();
    (() => {
        // cartAddRemoveDesignIcon(cookieNum[i].innerHTML, cart_icon_remove[i], cart_icon_add[i], img_url.src, all_artwork_data, elementSold[i]);

    })();
    visitArtwork(img_url, cookieNum[i].value);

}







slideNextPrevious();
const seeMore = getAllElement(".see-more");
const directive = getAllElement(".link-directive");
const seeInDetails = getAllElement(".see-in-details");
for (let x = 0; x < seeMore.length; x++) {
    seeInDetails[x].onclick = () => {
        for (let t = 0; t < seeMore.length; t++) {
            getAllElement(".artworks-grandparent")[t].style.display = "none";
            seeMore[t].style.display = "none";

            getAllElement(".artworks-category")[t].style.display = "none";

        }
        directive[x].style.display = "flex";
        getAllElement(".artworks-grandparent")[x].style.display = "flex";
        getAllElement(".allArtParent")[x].style.display = "flex";
        getAllElement(".artworks-category")[x].style.display = "block";

        getAllElement(".allArtParent")[x].style.flexWrap = "wrap";
        seeMore[x].style.display = "none";
        getAllElement(".parent-next2")[x].style.display = "none";
        getAllElement(".parent-previous2")[x].style.display = "none";
        getAllElement(".allArtParent")[x].style.justifyContent = "center";


        window.scrollTo(0, 0);


    }
}
// getElement("#slideshow-cancel-button").onclick = () => {
//     getElement(".artwork-details-info-popup").style.display = "none";
// }
// getElement(".image-link").onclick = () => {
//     window.location.href = "/artworks/00000" + getElement(".popup-image-id").innerHTML;
// }

// async function displayInventory(id) {
//     const response = await fetch(`/data/artworks/00000${id}/inventory`);
//     const inventory = await response.json();
//     getAllElement(".inventory-data")[id].value = inventory;
//     return inventory;
// }
