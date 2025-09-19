<!-- make sure #email-form and .loading exist in your page -->
import {getElement} from "./helperMethod.js";

const emailForm = document.querySelector('#email-form');
const loader = document.querySelector(".loading");
alert(90)
const isEmail = (v) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(v);

emailForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    alert(90)

    const first = getElement("#firstname-contact").value.trim();
    const last = getElement("#lastname-contact").value.trim();
    const email = getElement("#email-contact").value.trim();
    const phone = getElement("#phone-contact")?.value.trim() || "";
    const msg = getElement("#message-textarea").value.trim();

    if (!first || !last || !isEmail(email) || !msg) {
        alert("Please complete all required fields with a valid email.");
        return;
    }

    loader?.classList.remove("loading-checkout");
    loader?.classList.add("loader");

    const payload = {
        // Server ignores 'from' and uses domain address; this is kept for backward compatibility.
        emailFrom: email,
        emailTo: email, // user receives the acknowledgement
        subject: `${first} ${last}`,
        message:
            `Client Firstname: ${first}\n` +
            `Client Lastname: ${last}\n` +
            `Client Email: ${email}\n` +
            (phone ? `Client Phone Number: ${phone}\n` : "") +
            `${msg}`,
    };

    try {
        const res = await fetch(`${apiBaseUrl}/sendemail`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            credentials: "include",
            body: JSON.stringify(payload),
        });

        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        emailForm.reset();
        alert("Email sent successfully. Thanks for reaching out!");
    } catch (error) {
        console.error(error);
        alert("Failed to send email. Please try again or email myart@jeanyveshector.com.");
    } finally {
        if (loader) {
            loader.classList.add("loader--hidden");
            loader.addEventListener("transitionend", () => {
                loader.classList.remove("loader", "loader--hidden");
            }, {once: true});
        }
    }
});

