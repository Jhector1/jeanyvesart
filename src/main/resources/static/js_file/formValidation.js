import {getElement, onReloadAlert} from "./helperMethod.js";
//import {cardNumberElement} from "./payment_process.js";

class FormValidation {
    constructor() {
        this.allinputValid = true;
        this.creditCardFormat();
    }
    checkEmptyInput2(elementSelector, elementErrorSelector) {
        const element = elementSelector;
        const elementError = elementErrorSelector;

            if (element.value.length>0) {
                elementError.innerHTML = "";
                this.allinputValid=true;
            } else {
                this.allinputValid = false;
                elementError.innerHTML = "Please choose a quantity";
            }



    }
    checkEmptyInput(elementSelector, elementErrorSelector) {
        const element = elementSelector;
        const elementError = elementErrorSelector;
        for (let i = 0; i < element.length; i++) {
            if (element[i].validity.valid) {
                elementError[i].innerHTML = "it is valid";
            } else if (element[i].validity.valueMissing) {
                this.allinputValid = false;
                elementError[i].innerHTML = "This field is required";
            }


        }
    }


    validateEmail(selector) {
        if (!getElement(selector).validity.valueMissing) {

            const email = getElement(selector);
        if (email.validity.typeMismatch) {
            getElement(".email-errorMessage").innerHTML = "Please enter a valid email";
            this.allinputValid = false;
        } else {
            getElement(".email-errorMessage").innerHTML = "";

        }}
    }

    validateCVV(selector) {
        if (!getElement(selector).validity.valueMissing) {
            const cvv = this.#filterDigit(getElement(selector));
            if (cvv.length < 3 || (getElement(selector).value.length !== cvv.length)) {
                getElement(".cvv-errorMessage").innerHTML = "Hmm! The security doesn't seem right, please try again";
                this.allinputValid = false;
            } else {
                getElement(".cvv-errorMessage").innerHTML = "";

            }
        }
    }

    validatePhoneNumber(selector) {
        if (!getElement(selector).validity.valueMissing) {

            const phone = this.#filterDigit(getElement(selector));
            console.log(phone);
            if (phone.toString().length !== 10) {
                getElement(".phone-errorMessage").innerHTML = "Please enter a valid phone number";
                this.allinputValid = false;
            } else {
                getElement(".phone-errorMessage").innerHTML = "";

            }
        }
    }
    static validateEmail(email){
        const regex =  /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    }
    static validatePassword(password){
        const regex = /^(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$/;
        return regex.test(password);
    }
    creditCardFormat() {
        function replacer(match, p1, p2) {
            // p1 is non-digits, p2 digits, and p3 non-alphanumerics
            return [p1, p2].join(" ");
        }

    }

    phoneNumberFormat() {
        function replacer(match, p1, p2, p3) {
            // p1 is non-digits, p2 digits, and p3 non-alphanumerics
            return `(${p1})-${p2}-${p3}`;
        }

        getElement("#phone-number").addEventListener("keydown", () => {
            const newString = getElement("#phone-number").value.replace(/(\d{3})(\d{3})(\d{1,4})/, replacer);
            getElement("#phone-number").value = newString;
        });

    }

    #filterDigit(inputElement) {
        let pattern = /\d/g;
            return inputElement.value.match(pattern).join("");


    }
    validateStripeCreditCard(creditCardElement){
        creditCardElement.on('change', (event) => {
            const displayError = document.querySelector('.card-number-error');
            if (event.error) {
                displayError.textContent = event.error.message;
                this.allinputValid=false;
            } else {
                displayError.textContent = '';

            }
        });
    }
    ValidateCard(creditCardFieldInput) {
        if (!creditCardFieldInput.validity.valueMissing) {

            const cardNum = this.#filterDigit(creditCardFieldInput);
            console.log(cardNum);
            creditCardFieldInput.value=cardNum;
            if (isCardValid() === false) {
                getElement(".card-errorMessage").innerHTML = "Please enter a valid Credit Card";
                this.allinputValid = false;
            } else {
                getElement(".card-errorMessage").innerHTML = "";

            }

            function isCardValid() {
                function addDigit(digit) {

                    let sum = 0;
                    for (let u = 0; u < digit.length; u++) {
                        sum += Number(digit[u]);
                    }
                    return sum;

                }

                const num = cardNum.toString();
                if (cardNum.length < 13 || cardNum.length > 16) {
                    return false;
                }
                let secondDigitsAdd = 0;
                for (let y = num.length - 2; y >= 0; y -= 2) {
                    let multNum = Number(num[y]) * 2;
                    let strMultNum = multNum.toString();
                    strMultNum.length > 1 ? secondDigitsAdd += Number(addDigit(strMultNum)) : secondDigitsAdd += multNum;
                }

                let sumOddPlaces = 0;
                for (let y = num.length - 1; y >= 0; y -= 2) {
                    sumOddPlaces += Number(num[y]);

                }

                console.log(sumOddPlaces + secondDigitsAdd);

                return (sumOddPlaces + secondDigitsAdd) % 10 === 0;


            }
        }
    }
    checkAllInputValidation(checkout_boxes, index) {
        const allAddressInput = checkout_boxes[index].querySelectorAll(".shipping-address-input");
        const allErrorMessageElement = checkout_boxes[index].querySelectorAll(".errorMessage");

        this.checkEmptyInput(allAddressInput, allErrorMessageElement);
        if (index === 0) {
            this.validateEmail("#email");
        }


    }
}

export {FormValidation};