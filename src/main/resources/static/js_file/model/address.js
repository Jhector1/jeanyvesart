import {getElement} from "../helperMethod.js";

 class Address {
    constructor(type) {
        this.type = type;
        this.fullname = getElement(`#${this.type}_fullname`).value;
        this.zipcode = getElement(`#${this.type}_zipcode`).value;

        this.country = getElement(`#${this.type}_country`).value;
        this.apt = getElement(`#${this.type}_apt`).value;
        this.street = getElement(`#${this.type}_street`).value;
        this.city = getElement(`#${this.type}_city`).value;
        this.state = getElement(`#${this.type}_state`).value;
    }

}
export {Address}