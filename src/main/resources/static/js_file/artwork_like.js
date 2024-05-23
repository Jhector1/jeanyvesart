import {
    cartAddRemoveDesign, cartAddRemoveDesignIcon,
    getAllElement,
    getElement,
    likeOrNotDesign,
    slideNextPrevious,
    SlideshowIndex, visitArtwork
} from "./helperMethod.js";
import {Cookie, ElementManager} from "./allcookies.js";

fetch("http://localhost:9090/data/artworks/category/all").then(response => response.json()).then(data => {
    let count = 0;
    data.forEach(inventory => {

        let template = `
            <div class="artworks-grandparent">
                <main>
                    <div class="link-directive">
                        <a href="/artworks" class="directive directive-artworks">artworks</a>
                        <i class="directive fa-solid fa-arrow-right-long"></i>
                        <p class="directive directive-category" >${inventory.category}</p>
                    </div>

                    <h1 class="artworks-category" >${inventory.category}</h1>

                    <article class="allArtParent">
                        <button class="parent-previous2">
                            <i class="previous2 fa-solid fa-chevron-left"></i>
                        </button>`;

        inventory.inventories.forEach(product => {
            template += `<div class="frame">
                                <input type="hidden" class="image-id" style="display:none;" value="${product.myProduct.id}">
                                <a href="/artworks/00000${product.myProduct.id}" class="image-wrapper">
                                    <img class="artwork-data-img" src="${product.myProduct.imageUrl}" alt="${product.myProduct.title}"/>
                                </a>
                                <section class="description">
                                    <div class="sub-title">
                                        <p class="ellipsisText titre artwork-data">${product.myProduct.title}</p>
                                        <div class="parent-heart">
                                            <div class="liked-icon-form">
                                                <button type="submit" class="liked-icon heart-circle-liked-heart"><i style="color: blue;"
                                                                                                       id="liked-heart1"
                                                                                                       class="liked-art fa-solid fa-heart"></i>
                                                </button>
                                            </div>
                                            <div class="not-liked-icon-form">
                                                <button type="submit" class="not-liked-icon heart-circle-not-liked-heart"><i id="not-liked-heart1"
                                                                                                               class="liked-art fa-regular fa-heart"></i>
                                                </button>
                                            </div>
                                        </div>
               
                                    </div>
                    
                                    <section class="sub-title">
                                        <div class="description-sub-title">
                                         
                                            <p class="ellipsisText medium artwork-data">${product.myProduct.medium}</p>
                                            <p  class="ellipsisText size artwork-data">${product.myProduct.size}</p>
                                            <div class="price-wrapper">
                                                <small class="currency">$US</small><small
                                                     class="ellipsisText price artwork-data">${product.myProduct.price}</small>
                                            </div>
                                            <p style="display: none"  class="describe-artwork">${product.myProduct.description}</p>
                                        </div>
                                        <div style="align-self: flex-end" class="touch-mode">
                                            <div class="cart-icon cart-remove-box">
                                                <i style="color: cornflowerblue; font-size: 1.5rem" class="fa-sharp fa-solid fa-cart-plus"></i>
                                            </div>
                                            <div class="cart-icon cart-add-box"><i style="font-size: 1.5rem" class="fa-sharp fa-solid fa-cart-plus"></i></div>
                                        </div>
                                    </section>
                                    <h3 class="sold-item">Sold</h3>
                    
                                    <section class="hover-mode">
                                        <button class="cart-add cart-add-action">Add to Cart</button>
                                        <button class="cart-remove cart-remove-action">Remove From Cart</button>
                                    </section>
                                </section>
                            </div>`;
            count += 1;
            //visitArtwork(product.myProduct.imageUrl, product.myProduct.id);
        })
        template += `<button class="parent-next2">
                    <i class="next2 fa-solid fa-chevron-right"></i></button>

                </article>
            </main>
            <div class="see-more">
                <div class="see-in-details-wrapper">
                    <hr>
                    <h1 class="see-in-details" href="#">See More</h1>
                
                    <hr>
                </div>
                <i style="display: none" id="down-id" class="down fa-solid fa-chevron-down"></i>
            </div>
        </div>`;
        document.querySelector(".artworks-wrapper").innerHTML += template;


    })
    return count;
}).then((count) => {
    const all_frame = getAllElement(".frame");

    const cookieNum = getAllElement(".image-id");
    const liked_heart = getAllElement(".liked-icon");
    const not_liked_heart = getAllElement(".not-liked-icon");

// const cart_add = getAllElement(".cart-add-action");
// const cart_remove = getAllElement(".cart-remove-action");
    const elementSold = getAllElement(".sold-item");

    const artworkID = getAllElement(".image-id");
    let all_artwork_data;
    for (let y = 0; y < count; y++) {


        const all_artwork = all_frame[y].querySelectorAll(".artwork-data");
        const img_url = all_frame[y].querySelector(".artwork-data-img");

        all_artwork_data = new Map();
        all_artwork_data.set("id", artworkID[y].value);
        all_artwork_data.set("date", img_url.src);

        all_artwork_data.set("imageUrl", img_url.src);
        all_artwork_data.set("title", all_artwork[0].innerHTML);
        all_artwork_data.set("medium", all_artwork[1].innerHTML);
        all_artwork_data.set("size", all_artwork[2].innerHTML);
        all_artwork_data.set("price", all_artwork[3].innerHTML);
        //all_artwork_data.set("unique", unique[i].value);
        //all_artwork_data.set("inventory", getAllElement(".inventory-data")[i]);
        all_artwork_data.set("description", getAllElement(".describe-artwork")[y].innerHTML);
        likeOrNotDesign(cookieNum[y].value, not_liked_heart[y], liked_heart[y], all_artwork_data, elementSold[y]);

    }
}).then(() => {
    slideNextPrevious();
    const seeMore = getAllElement(".see-more");
    const directive = getAllElement(".link-directive");
    const seeInDetails = getAllElement(".see-in-details");
    for (let x = 0; x < seeMore.length; x++) {
        seeInDetails[x].onclick = () => {
            document.body.innerHTML+=`<div th:replace="~{dryTemplate.html :: spinner(message='One second...', dynamicClass='loader')}"></div>`;
            const loader = document.querySelector(".loading");
            loader.classList.remove("loading-checkout");
            loader.classList.add("loader");
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
}).catch(error => console.log(error));

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
let all_artwork_data;
for (let i = 0; i < all_frame.length; i++) {
    const all_artwork = all_frame[i].querySelectorAll(".artwork-data");
    const img_url = all_frame[i].querySelector(".artwork-data-img");

    all_artwork_data = new Map();
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
        likeOrNotDesign(cookieNum[i].value, not_liked_heart[i], liked_heart[i], all_artwork_data, elementSold[i]);
    })();

    (() => {
        // cartAddRemoveDesign(cookieNum[i].innerHTML, cart_remove[i], cart_add[i], img_url.src, all_artwork_data, elementSold[i]);

    })();
    (() => {
        // cartAddRemoveDesignIcon(cookieNum[i].innerHTML, cart_icon_remove[i], cart_icon_add[i], img_url.src, all_artwork_data, elementSold[i]);

    })();
    //visitArtwork(img_url, cookieNum[i].value);

}
