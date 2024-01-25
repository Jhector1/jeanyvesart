import {getAllElement, getElement} from "./helperMethod.js";

class Footer extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = `<head>
    <title></title>
<!--    <style>footer {-->
<!--    position: relative;-->
<!--    z-index: 1;-->
<!--    row-gap: 5%;-->
<!--    display: flex;-->
<!--    align-items: center;-->
<!--    flex-direction: column;-->
<!--    justify-content: center;-->
<!--    background-color: var(&#45;&#45;main-color);-->
<!--}-->

<!--.credit-card {-->
<!--    padding: 1% 0;-->
<!--    display: flex;-->
<!--    justify-content: space-around;-->
<!--    width: 50%;-->
<!--}-->

<!--.credit-card li i {-->
<!--    font-size: 1.5rem;-->
<!--}-->


<!--.base {-->
<!--    padding: 2% 0;-->
<!--    display: flex;-->
<!--    justify-content: space-around;-->
<!--    align-items: center;-->

<!--}-->

<!--/*previously 10% with Sygma logo*/-->

<!--.base .logo2 {-->
<!--    width: 10%;-->

<!--}-->

<!--.follow-us {-->
<!--    width: max-content;-->
<!--}-->

<!--.follow-us > *, .contact li {-->
<!--    padding: 2% 0;-->
<!--}-->

<!--.title, .contact li {-->
<!--    text-align: center;-->
<!--}-->

<!--.social-media {-->
<!--    display: flex;-->
<!--    margin: auto;-->
<!--    justify-content: space-between;-->
<!--}-->

<!--.social-media a {-->
<!--    color: black;-->
<!--}@media only screen and (max-width: 800px){-->
<!--    .base{-->
<!--        flex-direction: column;-->
<!--    }-->
<!--    .base .logo2{-->
<!--    width: 40%;-->
<!--    }-->
<!--    .follow-us{-->
<!--    width: 50%;-->
<!--    }-->
<!--}-->
<!--</style>-->
    <link rel="stylesheet" href="/scss/stylesheet/style2.css" type="text/css">
<!--   <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.21.0/font/bootstrap-icons.css" rel="stylesheet">-->
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" defer></script>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />   <title></title>
<!--    <style>-->
    </head>
    <body >
        <footer> 
            <div class="credit-card">
                <li><i class="fa-brands fa-cc-visa"></i></li>
                <li><i class="fa-brands fa-cc-mastercard"></i></li>
                <li><i class="fa-brands fa-cc-discover"></i></li>
                <li><i class="fa-brands fa-cc-amex"></i></li>
                <li><i class="fa-brands fa-cc-paypal"></i></li>
            </div>
            <section class="base">
                <address class="contact">
                    <li class="title">Contact Us</li>
                    <li>Email: myart@jeanyveshector.com</li>
                    <li>phone: 773 690 7299</li>
                </address>
                <div class="parent-logo2"><img class="logo2" style="width: 15rem; height: auto" src="/images/jeanyves_logo.svg" alt="Jean Yves Logo"/></div>
                <section class="follow-us">
                    <div class="title">Follow Us</div>
                    <nav class="social-media">
                        <a href="#" class="fa-brands fa-linkedin"></a>
                        <a href="#" class="fa-brands fa-youtube"></a>
                        <a href="https://www.facebook.com/yves.jean.50364/" class="fa-brands fa-facebook"></a>
                        <a href="https://www.instagram.com/therealjean.yves/" class="fa-brands fa-instagram"></a>
                   </nav>
                </section>
            </section>       
            <p style="text-align: center; padding:1rem; ">Copyright © 2023 Jean Yves Hector - All Rights Reserved</p>
<p style="font-size: 13px; padding: 2%; text-align: center;  margin:auto">Powered by @maSygLink</p>
        </footer>
    </body>`
    }
}

customElements.define("footer-component", Footer);
import {CookieClient} from "./allcookies.js";
import {getCookie, setCookie} from "./anonymous_user_cookie.js";

const cookieClient = new CookieClient();
cookieClient.addToClientBasket("C")
cookieClient.displayNumberLike(".cart-add-list", "cart");

const cookieClient2 = new CookieClient();
cookieClient2.addToClientBasket("L");
cookieClient2.displayNumberLike(".count-favorite-list", "favorite");
getElement(".menu").addEventListener('click', () => {
    if (getElement(".artwork-menu").style.display === "flex") {
        getElement(".artwork-menu").style.display = "none";
    } else {
        getElement(".artwork-menu").style.display = "flex";

    }
});

getElement(".log-user-out").addEventListener('click', function() {
    // This function will be executed when the button is clicked
    sessionStorage.setItem("login-username", "anonymous");

    console.log('Button clicked!');
});

setTimeout(()=> {
    if (sessionStorage.getItem("login-username") === "anonymous" || sessionStorage.getItem("login-username") === null) {
        getElement(".anonymous-user").style.display = "block";
        getElement(".user-icon-box").style.display = "none";

    } else {
        getElement(".user-icon-box").style.display = "block";
        getElement(".anonymous-user").style.display = "none";

    }


},1);
setUrl(".item1", "profile");
setUrl(".item2", "collections");
//setUrl(".item3", "payment-settings");
//setUrl(".item4", "myReviews");

function setUrl(selector, url_head){
    getElement(selector).href = `/account/${getCookie("session_Id")}/${url_head}`;
}
window.addEventListener('resize',showMenuHorizontally);
showMenuHorizontally();
function showMenuHorizontally(){
    // Get the width of the window
    const windowWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;

// Get the height of the window
    //  var windowHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

    if(windowWidth>501 &&  windowWidth < 800){
        getElement(".menu-to-show").classList.add("show");
    }
    else{
        getElement(".menu-to-show").classList.remove("show");

    }
}