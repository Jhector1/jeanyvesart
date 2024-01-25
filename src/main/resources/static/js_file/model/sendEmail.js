import {getElement} from "../helperMethod.js";
const emailForm = document.querySelector('#email-form');
const loader = document.querySelector(".loading");
emailForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    loader.classList.remove("loading-checkout");

    loader.classList.add("loader");

    fetch('/sendemail', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: getElement("#firstname-contact").value,
            lastName: getElement("#lastname-contact").value,
            emailFrom: getElement("#email-contact").value,
            emailTo: "myart@jeanyveshector.com",
            subject: getElement("#firstname-contact").value + " " + getElement("#lastname-contact").value,
            message: "Client Firstname: " + getElement("#firstname-contact").value
                + "\nClient Lastname: " + getElement("#lastname-contact").value
                + "\nClient Email: " + getElement("#email-contact").value
                + "\nClient Phone Number: " + getElement("#phone-contact").value
                + "\n" + getElement("#message-textarea").value,
            fileAttachment: getElement("#myfile").value,
        })
    }).then(function (response) {
            loader.classList.add("loader--hidden");
            loader.addEventListener("transitionend", () => {
                loader.remove();
            });
        document.querySelector("#email-form").reset();
            if (response.ok) {
                alert("email sent successfully");
            } else {
                alert("Failed to send email. Please ensure the provided email is valid. If the error persists, please contact us directly at myart@jeanyveshector.com.")
            }
            console.log(response);
        })
});