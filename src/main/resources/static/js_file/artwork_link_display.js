import {cartAddRemoveDesign, getAllElement, getElement, likeOrNotDesign} from "./helperMethod.js";

import {FormValidation} from "./formValidation.js";
import {ElementManager} from "./allcookies.js";
import {checkCookie} from "./anonymous_user_cookie.js";
import {ProductAndInventory} from "./model/product_and_Inventory.js";
console.log("hello world")
async function makeFetchSynchronous() {
    try {
        const url = `${apiBaseUrl}/data/artworks/${artworkID}`;
        console.log(url);
        const response = await fetch(url);
               const data = await response.json();
        const imageData = data.myProduct.imageUrl;
        const imageTitle = data.myProduct.title;
        const imageMedium = data.myProduct.medium;
        const imageSize = data.myProduct.size;
        const imageDescription = data.myProduct.description;
        const imagePrice = data.myProduct.price;


        document.querySelector(".artwork-details-info-popup").style.display = "flex";
        document.querySelector(".artwork-image").src = imageData;
        document.querySelector(".artwork-image").alt = imageTitle;

        const artworkDesc = document.querySelectorAll(".display-common");
        artworkDesc[0].innerHTML = imageTitle;
        artworkDesc[1].innerHTML = imageMedium;
        artworkDesc[2].innerHTML = imageSize;
        artworkDesc[3].innerHTML = imagePrice;
        const imageUrls = data.metadata;
        //getElement(".uniqueness").value = data.artwork.isUnique;
        document.querySelector(".popup-artwork-description").innerHTML = imageDescription;
        if (data.quantity <= 1) {
            getElement(".quantity-lbl").style.display = "none";
        }
        let images_container = getAllElement(".artwork-image-details");
        if (imageUrls.length > 0) {
            getElement(".detail-view-parent").style.display = "flex";
            images_container[0].src = imageUrls[0];
            images_container[1].src = imageUrls[1];
            images_container[2].src = imageUrls[2];
            images_container[3].src = imageUrls[3];
            images_container[4].src = imageUrls[4];

            images_container[0].alt = imageTitle;
            images_container[1].alt = imageTitle;
            images_container[2].alt = imageTitle;
            images_container[3].alt = imageTitle;
            images_container[4].alt = imageTitle;

            const container_images = getAllElement(".artwork-in-detail-view");
            for (let a = 0; a < images_container.length; a++) {
                images_container[a].onclick = (e) => {
                    container_images.forEach(value => value.style.border = "2px slategrey solid");
                    container_images[a].style.border = "2px cornflowerblue solid";
                    document.querySelector(".artwork-image").src = images_container[a].src;
                }
            }
        }
        const popup_cart_add = getElement(".popup-cart-add-action");
        const popup_cart_remove = getElement(".popup-cart-remove-action");
        const popup_liked_heart = getElement(".popup-liked-heart");
        const popup_not_liked_heart = getElement(".popup-not-liked-heart");
        const popup_element_sold = getElement(".popup-sold-item");
        const popup_purchase = getElement(".popup-purchase");

        const img_url = document.querySelector(".artwork-image");
        const common_op2 = document.querySelectorAll(".display-common");
        const cookieNum2 = artworkID.substring(5);
        const all_artwork_data = [imageData, imageTitle, imageMedium, imageSize, imagePrice, imageDescription, getElement("#quantity"), getElement(".quantity-error")];
        const map = new Map();
        map.set("description", imageDescription);

        map.set("imageUrl", imageData);
        map.set("title", imageTitle);
        map.set("medium", imageMedium);
        map.set("size", imageSize);
        map.set("price", imagePrice);
        map.set("quantity", getElement("#quantity"));
        map.set("quantity-error", getElement(".quantity-error"));
        map.set("inventory", getElement(".inventory-data"));


        likeOrNotDesign(cookieNum2, popup_not_liked_heart, popup_liked_heart,  map, popup_element_sold);
        //
        cartAddRemoveDesign(cookieNum2, popup_cart_remove, popup_cart_add, map, popup_element_sold, popup_purchase, 'display');
        navigateArtwork("#next", "#previous");
        navigateArtwork("#previous", "#next");

        function navigateArtwork(element1, element2) {
            getElement(element1).onclick = () => {
                element1 === "#next" ? window.location.href = "/artworks/00000" + (Number(cookieNum2) + 1) : window.location.href = "/artworks/00000" + (Number(cookieNum2) - 1);

                getElement(element2).style.visibility = "visible";

            }
        }


        checkIndexArrowState(Number(cookieNum2));

        function checkIndexArrowState(slideshowIndex) {
            if (slideshowIndex === Number(numOfArt - 1)) {
                getElement("#next").style.visibility = "hidden";

            } else if (slideshowIndex === 0) {

                getElement("#previous").style.visibility = "hidden";

            }
        }

        return data;


    } catch (error) {
        document.querySelector(".not-found").innerHTML = "Artwork Not Found";
    }
}

const stripe = Stripe(stripePublicKey2);

const customerId = checkCookie("user12345");

makeFetchSynchronous().then((data) => {
    const proceed_to_checkout = document.querySelector(".popup-purchase");
    const formValidation = new FormValidation();
    proceed_to_checkout.addEventListener('click', (event) => {
        let quantityItem = 0;
        if (Number(data.quantity) === 1) {
            quantityItem = 1;
        } else {
            ElementManager.validateQuantity(formValidation, getElement("#quantity"), getElement(".quantity-error"));

            quantityItem = getElement("#quantity").value;
        }

        if (formValidation.allinputValid) {
            const loader = document.querySelector(".loading");
            loader.classList.remove("loading-checkout");

            loader.classList.add("loader");
            const productAndInventory = new ProductAndInventory();

            productAndInventory.inventory = data.quantity;
            productAndInventory.myProduct = data.myProduct;
            productAndInventory.quantity = quantityItem;


            event.preventDefault();
            fetch(`${apiBaseUrl}/cart/checkout/create-checkout-session`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // 'X-IDENTIFIER': customerId.substring(customerId.length/2),
                    // //[csrfHeader]: csrfToken
                    // 'X-CSRF-TOKEN': document.querySelector(".csrf-token").value, //csrfToken,

                },
                body: JSON.stringify({cartProductList: [productAndInventory], customerId: customerId,})
            }).then(function (response) {
                // console.log(response.text())
                console.log(response);
                if (!response.ok) {
                    throw (new Error("Sorry something is wrong,please try again later" + response));
                }
                return response.text();
                // Handle the response from the server
            }).then((sessionId) => {

                stripe.redirectToCheckout({
                    sessionId: sessionId
                });

            }).catch((error) => {
                    loader.classList.add("loader--hidden");

                    loader.addEventListener("transitionend", () => {
                        loader.remove()
                    });
                    alert(error);
                }
            ).finally(() => loader.remove())
            ;
        }
    });

});

function previewFile(index) {
    // const preview = document.querySelector("img");
    const fileInput = document.querySelector(".file-image");
    const reader = new FileReader();
    const file = fileInput.files[0];

    reader.addEventListener(
        "load",
        () => {
            // convert image file to base64 string
            // console.log(reader.result.split(",")[1]);
            myData(index, reader.result.split(",")[1], file.name);
        },
        false,
    );

    if (file) {
        reader.readAsDataURL(file);
    }
}

function myData(rate, imageData = null, imageName = null) {


    getElement("#form-review").onsubmit = (event) => {

        event.preventDefault();


        const headline = getElement("#headline").value;
        const comment = getElement("#comment").value;
        const productId = artworkID.substring(5);
        // alert(imageName);
        // alert(imageData);
        // console.log(productId);
        //console.log(document.cookie)
        const userId = checkCookie("user12345");
        // console.log(userId);
        // console.log(artworkID)
        const loader = document.querySelector(".loading");
        loader.classList.remove("loading-checkout");
        getElement(".while-waiting").style.display="none";
        loader.classList.add("loader");
        fetch(`${apiBaseUrl}/product/review/save`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-IDENTIFIER': customerId.substring(customerId.length/2),
                //[csrfHeader]: csrfToken
                'X-CSRF-TOKEN': document.querySelector(".csrf-token").value, //csrfToken,

            },
            body: JSON.stringify({
                headline: headline,
                reviewer: {id: userId,},
                reviewText: comment,
                productId: productId,
                rating: rate,
                imageName: imageName,
                imageData: imageData,

            })
        })
            .then(function (response) {
                console.log(response);
                // console.log(response.text())

                if (!response.ok) {
                    alert("Sorry something is wrong,please try again later");
                } else {
                    loader.classList.add("loader--hidden");

                    loader.addEventListener("transitionend", () => {
                        location.reload();
                        loader.remove();

                    });
                    console.log("comment add successfully");
                }

                // Handle the response from the server
            })

    }
}

function showContent(headline, reviewText, reviewerName, date, rate, imageData) {
    let temp = document.querySelector("template");
    let clon = temp.content.cloneNode(true);
    clon.querySelector(".review-headline").innerHTML = headline;
    clon.querySelector(".review-text").innerHTML = reviewText;
    clon.querySelector(".reviewer-name").innerHTML = reviewerName + " ";

    const currentDate = new Date(date);
    const options = {
        month: 'short',
        day: 'numeric',
        year: 'numeric',
    };

    const formatter = new Intl.DateTimeFormat('en-US', options);
    clon.querySelector(".review-date").innerHTML = formatter.format(currentDate);
    const image = clon.querySelector("#commented-image");
    if (imageData) {
        image.style.display = "block";
        image.src = `data:image/webp;base64,${imageData}`;

    } else {
        image.style.display = "none";

    }
    const star_reviewed = clon.querySelectorAll(".star-reviewed");
    for (let i = 0; i <= rate; i++) {
        star_reviewed[i].src = "/images/icons/star_review.svg";
    }

    document.querySelector(".parent-reviews").appendChild(clon);
}

displayReviews().then(r => console.log(r));

async function displayReviews() {
    try {
        const product_id = artworkID.substring(5);

        const response = await fetch(`${apiBaseUrl}/product/review/all/${product_id}`);
        //console.log(response)

        const listReviews = await response.json();
        console.log(listReviews)

        if (listReviews.length > 0) {
            listReviews.forEach(value => {
                showContent(value.headline, value.reviewText, value.reviewer.fullName, value.date, value.rating, value.imageData);
                //console.log(value)

            })
        }


        return listReviews;

    } catch (error) {
        console.log(error)
    }

}

getRate();

function getRate() {
    const allStar = getAllElement(".star-blank");
    //console.log(allStar)
    let u = 0;


    for (let index = 0; index < allStar.length; index++) {
        allStar[index].onclick = () => {
            allStar.forEach(star => {
                star.src = "/images/icons/star_blank.svg";
            });
            for (let y = 0; y <= index; y++) {
                allStar[y].src = "/images/icons/star_review.svg";

            }
            const fileInput = getElement(".file-image");

            fileInput.onchange = () => {
                const file = fileInput.files[0];
                if(file && file.type.startsWith("image/")){
                previewFile(index);
                return 0;}
                else{
                    fileInput.value=null;
                    alert("Unsupported file")
                }
            }

            myData(index);

            getElement("#submit-review").disabled = false;

            getElement(".file-image").disabled = false;

        }

    }
    //return u;

}
