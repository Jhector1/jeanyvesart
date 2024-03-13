import {getCookie} from "./anonymous_user_cookie.js";

class JsonRequest {
    constructor() {
    }


   static post(url, obj) {
        // const csrfToken = document.querySelector(".csrf-token").value;
        // console.log(csrfToken);
        const customerId = getCookie("user12345");
        return fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',

                'X-IDENTIFIER': customerId.substring(customerId.length/2),
                //[csrfHeader]: csrfToken
                'X-CSRF-TOKEN': document.querySelector(".csrf-token").value, //csrfToken,


            },
            body: JSON.stringify(obj)
        });

    }
    get(url){
        fetch(url).then(response => response.json());
    }
}
export {JsonRequest};