import { getElement } from "../helperMethod.js";
import { JsonRequest } from "../json_request.js";

const emailForm = document.querySelector("#email-form");
const loader = document.querySelector(".loading");
const submitBtn = document.querySelector("#submit-message");

const setBusy = (busy) => {
    // disable all fields inside the form
    emailForm.querySelectorAll("input, textarea, button, select").forEach((el) => {
        el.disabled = busy;
    });

    // button loading UI
    if (busy) {
        submitBtn.classList.add("is-loading");
        submitBtn.querySelector(".btn-label").textContent = "Sending…";
    } else {
        submitBtn.classList.remove("is-loading");
        submitBtn.querySelector(".btn-label").textContent = "Send Message";
    }

    // overlay
    if (loader) loader.classList.toggle("is-active", busy);

    // accessibility
    emailForm.setAttribute("aria-busy", busy ? "true" : "false");
};

emailForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    // get fresh token BEFORE disabling (important)
    const turnstileToken =
        document.querySelector('input[name="cf-turnstile-response"]')?.value;

    if (!turnstileToken) {
        alert("Please complete the security check and try again.");
        return;
    }

    // prevent double submit
    if (submitBtn.disabled) return;

    setBusy(true);

    try {
        const response = await JsonRequest.post(`${apiBaseUrl}/sendemail`, {
            firstName: getElement("#firstname-contact").value,
            lastName: getElement("#lastname-contact").value,
            emailFrom: "myart@jeanyveshector.com",
            emailTo: getElement("#email-contact").value,
            subject:
                getElement("#firstname-contact").value +
                " " +
                getElement("#lastname-contact").value,
            message:
                "Client Firstname: " + getElement("#firstname-contact").value +
                "\nClient Lastname: " + getElement("#lastname-contact").value +
                "\nClient Email: " + getElement("#email-contact").value +
                "\nClient Phone Number: " + getElement("#phone-contact").value +
                "\n" + getElement("#message-textarea").value,
            fileAttachment: getElement("#myfile").value,

            turnstileToken,
        });

        // reset Turnstile for next submit
        if (window.turnstile) window.turnstile.reset();

        emailForm.reset();

        if (response.ok) {
            alert("Email sent successfully");
        } else {
            alert(
                "Failed to send email. Please try again. If it persists, contact us at myart@jeanyveshector.com."
            );
        }
    } catch (e) {
        // also reset Turnstile on network failure
        if (window.turnstile) window.turnstile.reset();
        alert("Fail to send request, please try again");
    } finally {
        setBusy(false);
    }
});
