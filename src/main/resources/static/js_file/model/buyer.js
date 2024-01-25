import {getElement} from "../helperMethod.js";
import {Address} from "./address.js";

export class Buyer{
    constructor() {
        this.email = getElement("#email").value;
        this.phoneNumber = getElement("#phone-number").value;
        this.address = new Address("s");
    }

}