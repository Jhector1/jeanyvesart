import { CookieClient } from "./allcookies.js";
import {
    artwork_template_visitor_manager,
    cartAddRemoveDesign,
    cartAddRemoveDesignIcon,
    clearCookieBasket,
    getAllElement,
    getElement,
    removeItem,
    slideNextPrevious,
    totalItemsPrice,
    visitArtwork,
} from "./helperMethod.js";
import { checkCookie, deleteCookie } from "./anonymous_user_cookie.js";
import { sharing } from "./social_media_share.js";

/* --------------------------- tiny DOM/loader helpers --------------------------- */
const $ = (s, r = document) => r.querySelector(s);
const $$ = (s, r = document) => Array.from(r.querySelectorAll(s));

function showLoader(el) {
    if (!el) return;
    el.style.display = "flex";              // ensure visible even if CSS had display:none
    el.classList.remove("loader--hidden");
}
function hideLoader(el) {
    if (!el) return;
    el.classList.add("loader--hidden");
    el.addEventListener("transitionend", () => {
        el.style.display = "none";
    }, { once: true });
}
function hideAllLoaders() {
    $$(".loader, .loading-checkout").forEach(hideLoader);
}

/* ------------------------------ main boot sequence ----------------------------- */
(async function initCartPage() {
    try {
        // 1) Build the cart list from DB
        const data = await new CookieClient().displayArtworkDataFromDB(
            ".parent-cart-list",
            ".cart-list",
            ".cart-common-op",
            "cart"
        );

        // 2) Attach remove-item handlers
        removeItem("cart", ".remove-cart-item", data);

        // 3) Clear Cart
        const clearBtn = getElement(".clear-cart");
        if (clearBtn) {
            clearBtn.onclick = () => {
                if (confirm("This action will clear\nyour cart list")) {
                    clearCookieBasket("cart");
                }
            };
        }

        // 4) Totals + share buttons
        let total = 0;
        const shareButtons = getAllElement(".share-to-media"); // [0] may be a hidden template
        for (let i = 0; i < data.length; i++) {
            const row = data[i];
            total += Number(row.quantity) * Number(row.myProduct.price);
            if (shareButtons[i + 1]) {
                sharing(row.myProduct.title, row.myProduct.id, shareButtons[i + 1]);
            }
        }
        const totalEl = getElement(".item-total");
        if (totalEl) totalEl.innerHTML = `$${total}`;
        CookieClient.displayDataLength(".cart-total-items", data);

        // 5) Extras on page
        artwork_template_visitor_manager();
        slideNextPrevious();
    } catch (err) {
        console.error(err);
    } finally {
        // Always hide any loader overlays after boot (success or fail)
        hideAllLoaders();
    }
})();

/* ------------------------------ stripe checkout ------------------------------ */
// `stripePublicKey` is provided by the page (Thymeleaf inline script)
let stripe = null;
try {
    if (typeof Stripe === "function" && typeof stripePublicKey === "string" && stripePublicKey) {
        stripe = Stripe(stripePublicKey);
    } else {
        console.warn("Stripe not initialized: missing library or public key.");
    }
} catch (e) {
    console.warn("Stripe initialization failed:", e);
}

const proceed_to_checkout = document.querySelector("#proceed-checkout-form");
if (proceed_to_checkout) {
    proceed_to_checkout.addEventListener("submit", async (event) => {
        event.preventDefault();

        // Show the dedicated checkout loader (fallback to global if needed)
        const checkoutLoader = $(".loading-checkout") || $(".loader");
        showLoader(checkoutLoader);

        try {
            if (!stripe) throw new Error("Payment is temporarily unavailable. Please try again later.");

            const customerId = checkCookie("user12345");

            const itemsRes = await fetch(`${apiBaseUrl}/cart/artworks/${customerId}`);
            if (!itemsRes.ok) throw new Error("Unable to fetch cart items.");
            const cartData = await itemsRes.json();

            const resp = await fetch(`${apiBaseUrl}/cart/checkout/create-checkout-session`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "X-IDENTIFIER": customerId.substring(customerId.length / 2),
                    "X-CSRF-TOKEN": document.querySelector(".csrf-token").value,
                },
                body: JSON.stringify({ cartProductList: cartData, customerId }),
            });

            if (!resp.ok) {
                throw new Error("Sorry, something went wrong creating the checkout session. Please try again.");
            }

            const sessionId = await resp.text();

            // Keep the loader visible while navigating to Stripe
            const { error } = await stripe.redirectToCheckout({ sessionId });
            if (error) throw error;
        } catch (error) {
            // Hide loader and report the error if anything fails
            hideLoader(checkoutLoader);
            alert(error);
        }
        // No finally: on success, we navigate away (we want loader to remain)
    });
}

/* ------------------------------ optional helper ------------------------------ */
function reloadLocationOnCardAdding(cart_remove, cart_add) {
    for (let y = 0; y < cart_remove.length; y++) {
        cart_remove[y].addEventListener("click", () => location.reload());
        cart_add[y].addEventListener("click", () => location.reload());
    }
}
