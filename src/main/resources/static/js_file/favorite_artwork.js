import {
    cartAddRemoveDesign,
    offerDecisionResponse,
    clearCookieBasket,
    getAllElement,
    getElement,
    removeItem,
    toggleCloseIcon
} from "./helperMethod.js";

const text_response_decision = document.querySelector(".input-phone");
const message_decision = document.querySelector(".input-message");
const cancel_button = document.querySelector("#cancel-button");

cancel_button.onclick = () => {

    document.querySelector(".mask-background").style.display = "none";
}
import {CookieClient} from "./allcookies.js";
import {sharing} from "./social_media_share.js";
import {getCookie} from "./anonymous_user_cookie.js";
import {JsonRequest} from "./json_request.js";


(async () => {
    const data = await new CookieClient().displayArtworkDataFromDB(".parent-wishlist", ".wish-list", ".common-op", "favorite");
    removeItem("favorite", ".remove-like", data);
    getElement(".clear-favorite").onclick = () => {
        if (confirm("This action will clear\nyour favorite artwork list"))

            clearCookieBasket("favorite");
    }
    const elementSold = getAllElement(".favorite-sold-item");
    const cart_add = getAllElement(".add-to-cart");
    const cart_remove = getAllElement(".remove-from-cart");
    const make_an_offer = getAllElement(".make-an-offer");
    for (let i = 0; i < data.length; i++) {

        const all_artwork_data = new Map();
        //[data[i].artwork.image_url, data[i].artwork.title, data[i].artwork.medium, data[i].artwork.size, data[i].artwork.price, data[i].artwork.isUnique, getAllElement("#quantity")[i+1], getAllElement(".quantity-error")[i+1], getAllElement(".inventory-data")[i+1]];

        all_artwork_data.set("id", data[i].myProduct.id);

        all_artwork_data.set("imageUrl", data[i].myProduct.imageUrl);
        all_artwork_data.set("title", data[i].myProduct.title);
        all_artwork_data.set("medium", data[i].myProduct.medium);
        all_artwork_data.set("size", data[i].myProduct.size);
        all_artwork_data.set("price", data[i].myProduct.price);
        all_artwork_data.set("quantity-value", getAllElement("#quantity")[i + 1].value);

        all_artwork_data.set("quantity", getAllElement("#quantity")[i + 1]);
        all_artwork_data.set("quantity-error", getAllElement(".quantity-error")[i + 1]);
        all_artwork_data.set("inventory", getAllElement(".inventory-data")[i + 1]);
        all_artwork_data.set("description", data[i].myProduct.description);

        cartAddRemoveDesign(data[i].myProduct.id, cart_remove[i + 1], cart_add[i + 1], all_artwork_data, elementSold[i + 1], make_an_offer[i]);


    }


    const wish_list = getAllElement(".wish-list");
    if (screen.width < 800) {
        for (let r = 1; r < wish_list.length; r++) {
            wish_list[r].style.display = "block";
        }
    }

    const wish_artwork_img = getAllElement(".wish-list img");
    const offer_img = getElement(".painting-offer img");
    const offer_data = getAllElement(".offer-data");
    const mask_background = getElement(".mask-background");


    let price = "";
    const offer_input = document.querySelector("#offer");

    const offer_percentage = document.querySelector(".offer-percentage");
    for (let n = 0; n < wish_list.length; n++) {
        const common_op = wish_list[n].querySelectorAll(".common-op");
        make_an_offer[n].onclick = () => {
            offerDecisionResponse(text_response_decision, ".phone-textarea");
            offerDecisionResponse(message_decision, ".message-textarea");
            mask_background.style.display = "flex";
            offer_img.src = wish_artwork_img[n].src;
            for (let r = 0; r < common_op.length; r++) {
                offer_data[r].innerHTML = common_op[r].innerHTML;

            }
            price = offer_data[3].innerHTML;
            const original_price = Number(price);
            offer_input.onkeyup = () => {


                if (Number(offer_input.value) >= 1 && Number(offer_input.value )<= original_price) {
               offer_percentage.style.visibility = "visible";
                    offer_percentage.innerHTML =`${Math.round(((((Number(offer_input.value) * 100) / original_price) + Number.EPSILON) - 100) * 100) / 100}%`;
                    //offer_percentage.style.backgroundColor = "slategrey";
                } else {
                   offer_percentage.style.visibility = "hidden";
                }
            };
        }
    }

    return data;

})().then(data => {
    const shareButton = getAllElement(".share-to-media");

    CookieClient.displayDataLength(".favorite-total-items", data);

    for (let o = 0; o < data.length; o++) {

        sharing(data[o].myProduct.title, data[o].myProduct.id, shareButton[o + 1]);
    }
});
const offerForm = getElement(".offer-form");
const loader = document.querySelector(".loading");

offerForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    let textM = "submitting Your offer...";

    getElement("#offer-submit").innerHTML = `<span style=" position: absolute; top:50%; right: 50%" class='spinner-grow spinner-grow-sm'></span> ${textM}`
    getElement("#offer-submit").disabled = true;
    getAllElement(".cancel").forEach(btn => btn.disabled = true);
    loader.classList.remove("loading");
    loader.classList.add("loader");
    const customerId = getCookie("user12345");
    setTimeout(() => {
        JsonRequest.post(`${apiBaseUrl}/sendemail`, {
            emailFrom: "jeanyveshector@gmail.com",
            emailTo: "myart@jeanyveshector.com",
            subject: "Offer for " + getElement(".offer-titre").innerHTML,
            message: "Client Email: " + getElement("#email").value
                + "\nClient Phone Number: " + getElement("#phone-number").value
                + "\nClient offer: US$" + getElement("#offer").value
                + "\nClient Message: " + getElement("#message-input").value
                + "\n\n\nOffer Description:\n"
                + "Title: " + getElement(".offer-titre").innerHTML
                + "\nMedium: " + getElement(".offer-medium").innerHTML
                + "\nSize: " + getElement(".offer-size").innerHTML
                + "\nPrice: US$" + getElement(".offer-price").innerHTML

        })
            // fetch(`${apiBaseUrl}/sendemail`, {
            //     method: 'POST',
            //     headers: {
            //         'X-IDENTIFIER': customerId.substring(customerId.length/2),
            //         //[csrfHeader]: csrfToken
            //         'X-CSRF-TOKEN': document.querySelector(".csrf-token").value, //csrfToken,
            //
            //     },
            //     body: JSON.stringify({
            //         emailFrom: "jeanyveshector@gmail.com",
            //         emailTo: "Sygmalink@gmail.com",
            //         subject: "Offer for " + getElement(".offer-titre").innerHTML,
            //         message: "Client Email: " + getElement("#email").value
            //             + "\nClient Phone Number: " + getElement("#phone-number").value
            //             + "\nClient offer: US$" + getElement("#offer").value
            //             + "\nClient Message: " + getElement("#message-input").value
            //             + "\n\n\nOffer Description:\n"
            //             + "Title: " + getElement(".offer-titre").innerHTML
            //             + "\nMedium: " + getElement(".offer-medium").innerHTML
            //             + "\nSize: " + getElement(".offer-size").innerHTML
            //             + "\nPrice: US$" + getElement(".offer-price").innerHTML
            //
            //     })
            // })
            .then(function (response) {

                if (response.ok) {
                    getElement(".mask-background").style.display = "none";
                    alert("Your offer has been sent successfully, \nsomeone will contact you for negotiation");
                } else {
                    alert("Fail to send request, please try again")
                }
                console.log(response);
            }).catch(error => alert("Fail to send request, please try again"))
            .finally(final => {
                    loader.classList.add("loader--hidden");
                    loader.addEventListener("transitionend", () => {
                        loader.remove();
                    });
                    textM = "Submit your Offer";
                    getAllElement(".cancel").forEach(btn => btn.disabled = false);
                    getElement("#offer-submit").disabled = false;
                    getElement("#offer-submit").innerHTML = `${textM}`;

                    offerForm.reset();
                }
            )
    }, 1000);
});


toggleCloseIcon(".contact-me");



