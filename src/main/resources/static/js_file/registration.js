import {getAllElement, getElement} from "./helperMethod.js";
import {JsonRequest} from "./json_request.js";
import {FormValidation} from "./formValidation.js";

function toggle_form(selectorOnClick, selectorDisplay, selectorHidden) {
    getElement(selectorOnClick).addEventListener("click", () => {
        getElement(selectorDisplay).style.display = "block";
        getElement(selectorHidden).style.display = "none";

    });
}

toggle_form(".sign-in", ".sign-in-form", ".sign-up-form");
toggle_form(".sign-up", ".sign-up-form", ".sign-in-form");

getElement(".sign-up-form").onsubmit = (event) => {
    event.preventDefault();
    const jsonRequest = new JsonRequest();
    const email = getElement(".email-registration").value;
    const password = getElement(".password-registration").value;
    const fullName = getElement(".full-name-registration").value;
    if (!validateAllInput()) {
        alert("Check your input and make you respect the expected pattern")
    } else {

        JsonRequest.post("/customer/register", {
            email: email, password: password, fullName: fullName,
        })
            .then(response => {
                //console.log(response.json())
                if (response.ok) {
                    getElement(".sign-in-form").style.display = "block";
                    getElement(".sign-up-form").style.display = "none";
                } // } else if (response.status === 409) {
                //     // If the response status is 409 (Conflict), handle it
                //     return response.json().then(data => {
                //         throw new Error(`Conflict! Status: ${response.status}, Message: ${data.result}`);
                //     });
                // }
                //
                if (!response.ok) {
                    if (response.status === 409) {
                        return response.text().then(text => {
                            throw new Error(`.email-registration~Conflict! Response status: ${response.status}, Message: ${text}`);
                        })
                    } else if (response.status === 400) {

                        return response.text().then(text => {
                            if (text.includes("Password"))
                                throw new Error(`.password-registration~Validation Failed: Response status ${response.status}, Message: ${text}`);
                            else
                                throw new Error(`.email-registration~Validation Failed: Response status ${response.status}, Message: ${text}`);

                        })
                    } else {
                        throw new Error(`.global-error~Internal Server Error: Response status ${response.status}. Please try
                    again at a later time. If the error persist, please email me at myart@jeanyveshector.com. Thank you `);
                    }
                }

            }).catch(err => {
            const arrErr = err.message.split("~");
            const elementInput = getElement(arrErr[0]);
            elementInput.classList.add("is-invalid");
            elementInput.classList.remove("is-valid");

            let elementSibling = elementInput.nextElementSibling;
            while (elementSibling && !elementSibling.classList.contains("invalid-feedback")) {

                elementSibling = elementSibling.nextElementSibling;
            }
            elementSibling.innerHTML = arrErr[1];
            console.log(err)

        });
    }
};

checkInputValidation(".email-registration", FormValidation.validateEmail);
checkInputValidation(".password-registration");

function checkInputValidation(selector, validate = FormValidation.validatePassword) {
    getElement(selector).addEventListener("input", () => {
        checkElementValidate(selector, validate);
    })
}

function checkElementValidate(selector, validate) {
    const element = getElement(selector);

    if (validate(element.value)) {
        element.classList.add("is-valid");
        element.classList.remove("is-invalid");
        return true;

    } else {
        element.classList.remove("is-valid");

        element.classList.add("is-invalid");
        return false;

    }
}

getElement(".signup-button").addEventListener('click', () => {

})
// getElement("#sign-cancel-button").onclick=()=>{
//     getElement(".sign-up-in-body").style.display='none';
// }
// getElement(".sign-user").onclick=()=>{
//     getElement(".sign-up-in-body").style.display='flex';
// }

function validateAllInput() {
    //alert("The form was submitted");
    return checkElementValidate(".email-registration", FormValidation.validateEmail) && checkElementValidate(".password-registration", FormValidation.validatePassword);

}

getElement(".form-reset-email").onsubmit = (event) => {
    event.preventDefault();
    const email = getElement("#email-reset").value;
    let textM = "Emailing Reset Password Link...";

    getElement(".btn-reset").innerHTML = `<span style=" position: absolute; top:50%; right: 50%" class='spinner-grow spinner-grow-sm'></span> ${textM}`
    getElement(".btn-reset").disabled = true;
    getAllElement(".btn-reset-close").forEach(btn=>btn.disabled=true);
    setTimeout(() => {
        JsonRequest.post(`${apiBaseUrl}/reset-password-email`, {
            emailTo: email,
        })
            .then(response => {
                //console.log(response.json())
                if (response.ok) {
                    getElement(".card-success").style.display = "block";
                    response.text().then(text => getElement(".card-success-body").innerHTML = text);


                    getElement(".card-to-send").style.display = "none";

                } // } else if (response.status === 409) {
                //     // If the response status is 409 (Conflict), handle it
                //     return response.json().then(data => {
                //         throw new Error(`Conflict! Status: ${response.status}, Message: ${data.result}`);
                //     });
                // }
                //
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(`Reset email failed: ${response.status}, Message: ${text}`);
                    })
                }

            }).catch(err => {
            textM = "Reset"
            getElement(".reset-email-error").innerHTML = err;


        }).finally(()=>{
            getAllElement(".btn-reset-close").forEach(btn=>btn.disabled=false);
            getElement(".btn-reset").disabled = false;
            getElement(".btn-reset").innerHTML = `${textM}`;

        })}, 1000);

};


