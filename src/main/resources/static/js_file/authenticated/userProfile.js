import {checkCookie} from "../anonymous_user_cookie.js";

const url = `${apiBaseUrl}/customer/account/update/${checkCookie("user12345")}`;
const form = document.querySelector("#addressForm");

form.addEventListener("submit", async (event) => {
    event.preventDefault();
    const loader = document.querySelector(".loading");
    loader.classList.remove("loading-checkout");

    loader.classList.add("loader");
    const formData = new FormData(form);
    const address={};

    console.log(formData)
    for (const [key, value] of formData.entries()) {
        address[key] = value;
        console.log(`${key}: ${value}`);
    }
    fetch(url, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            // You may need to include other headers like authentication tokens here
        },
        body:JSON.stringify( {
            address: address,
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(updatedData => {
            console.log('Resource updated successfully:', updatedData);
        })
        .catch(error => {
            console.error('Error updating resource:', error);
        }).finally(() => loader.remove());
});


const form2 = document.querySelector("#userInfoForm");

form2.addEventListener("submit", async (event) => {
    event.preventDefault();
    const loader = document.querySelector(".loading");
    loader.classList.remove("loading-checkout");

    loader.classList.add("loader");
    const formData2 = new FormData(form2);
    const myCustomer={};
    for (const [key, value] of formData2.entries()) {
        myCustomer[key] = value;
        console.log(`${key}: ${value}`);
    }

    fetch(url, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            // You may need to include other headers like authentication tokens here
        },
        body:JSON.stringify(myCustomer)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(updatedData => {
            console.log('Resource updated successfully:', updatedData);
        })
        .catch(error => {
            console.error('Error updating resource:', error);
        }).finally(() => loader.remove());
});


