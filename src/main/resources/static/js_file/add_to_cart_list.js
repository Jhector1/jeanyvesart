import {CookieClient} from "./allcookies.js";
import {
    artwork_template_visitor_manager,
    cartAddRemoveDesign, cartAddRemoveDesignIcon,
    clearCookieBasket,

    getAllElement,
    getElement, removeItem, slideNextPrevious, totalItemsPrice, visitArtwork,
} from "./helperMethod.js";
import {checkCookie, deleteCookie} from "./anonymous_user_cookie.js";
import {sharing} from "./social_media_share.js";

(async () => {
    const data = await new CookieClient().displayArtworkDataFromDB(".parent-cart-list", ".cart-list", ".cart-common-op", "cart");
    removeItem("cart", ".remove-cart-item", data);
    getElement(".clear-cart").onclick = () => {
        if (confirm("This action will clear\nyour cart list"))

            clearCookieBasket("cart");
    }
    return data;
})().then(data => {
    const shareButton = getAllElement(".share-to-media");

    let total = 0;
    for (let price = 0; price < data.length; price++) {
        total += Number(data[price].quantity) * Number(data[price].myProduct.price);
        sharing(data[price].myProduct.title, data[price].myProduct.id, shareButton[price + 1]);
    }
    const total_item = getElement(".item-total");
    total_item.innerHTML = `$${total}`;
    CookieClient.displayDataLength(".cart-total-items", data);

});
function reloadLocationOnCardAdding(cart_remove, cart_add) {
    for (let y = 0; y < cart_remove.length; y++) {
        cart_remove[y].addEventListener('click', () => {
            location.reload();
        });
        cart_add[y].addEventListener('click', () => {
            location.reload();
        });
    }
}

const stripe = Stripe(stripePublicKey);


const proceed_to_checkout = document.querySelector("#proceed-checkout-form");
proceed_to_checkout.addEventListener('submit', async (event) => {
    const loader = document.querySelector(".loading");
    loader.classList.remove("loading-checkout");
    loader.classList.add("loader");
    event.preventDefault();
    const customerId = checkCookie("user12345");
    const response = await fetch(`${apiBaseUrl}/cart/artworks/${customerId}`);
    const data = await response.json();

    console.log(data);
    fetch(`${apiBaseUrl}/cart/checkout/create-checkout-session`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            //'Content-Type': 'application/json' ,// Set the content type based on your API requirements
            'X-IDENTIFIER': customerId.substring(customerId.length/2),
            //[csrfHeader]: csrfToken
            'X-CSRF-TOKEN': document.querySelector(".csrf-token").value, //csrfToken,
        },
        body: JSON.stringify({
            cartProductList: data,
            customerId: customerId,

        })
    })
        .then(function (response) {
            console.log(response);
            // console.log(response.text())

            if (!response.ok) {
                throw new Error("Sorry something is wrong,please try again later");
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
});
artwork_template_visitor_manager();
slideNextPrevious();
