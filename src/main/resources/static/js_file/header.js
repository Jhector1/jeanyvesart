import {getElement} from "./helperMethod.js";
//
// class Header extends HTMLElement {
//     constructor() {
//         super();
//
//     }
//
//
//     connectedCallback() {
//         // this.attachShadow({mode: 'closed'});
//         // const shadow = this.attachShadow({mode: 'open'});
//
//         this.innerHTML = `<head>
//   <!-- Bootstrap CSS -->
//  <meta name="viewport" content="width=device-width, initial-scale=1">
//        <link rel="stylesheet" href="/stylesheet/scss/styles.css" type="text/css">
//
// <!--  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">-->
//   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" defer></script>
//   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />   <title></title>
// <!--    <style>-->
// <!--     Header {-->
// <!--   -->
// <!--    padding: 1% 0;-->
// <!--    display: flex;-->
// <!--    width: inherit;-->
// <!--//height: 6 %;-->
// <!--    background-color: var(&#45;&#45;main-color);-->
// <!--    align-items: center;-->
// <!--   -->
// <!--    border-bottom: 3px red solid;-->
// <!--    justify-content: space-around;-->
// <!--}-->
// <!--.header-wrapper{-->
// <!-- z-index: 2;-->
// <!--display: flex;-->
// <!--flex-direction: column;-->
// <!--align-items: center;-->
// <!--justify-content: center;-->
// <!--background-color: #eeeeee;-->
// <!--border: var(&#45;&#45;border-less-opacity);-->
// <!--width: 100vw;-->
// <!-- position: sticky;-->
// <!--    top: 0;-->
// <!--}-->
// <!--header *{ -->
//
// <!--color: black;-->
// <!--}-->
//
// <!--.home, .nav-link ,nav, .repo-icon {-->
// <!--    display: flex;-->
// <!--    align-items: center;-->
//
// <!--    justify-content: space-between;-->
//
// <!--}-->
//
// <!--nav li, .repo-icon li, .credit-card li, .contact li {-->
// <!--    list-style-type: none;-->
//
// <!--}-->
//
// <!--nav, .nav-link {-->
// <!--    width: max-content;-->
// <!--    gap: 2rem;-->
// <!--}-->
//
// <!--.home {-->
// <!--    width: max-content;-->
// <!--justify-self: center;-->
// <!--}-->
//
// <!--.repo-icon {-->
// <!--    gap: 2rem;-->
// <!--    width: max-content;-->
// <!--}-->
//
// <!--li a, a {-->
// <!--    text-decoration: none;-->
//
// <!--}-->
// <!--.nav-link {-->
// <!--margin: auto;-->
// <!--display: none;-->
// <!--padding-top: 1rem;-->
// <!--}-->
// <!--.logo {-->
// <!--    /*previously 30px with Sygma logo*/-->
// <!--    width: 30px;-->
// <!--    height: auto;-->
// <!--}-->
// <!--.bar-nav{-->
// <!--display: none;-->
// <!--width: max-content;}-->
// <!--.fa-bars{-->
// <!--font-size:2rem ;-->
// <!--}-->
// <!--nav{-->
//
// <!--}-->
// <!--.menu{-->
// <!--display: none;-->
// <!--}-->
//
// <!--.artwork-menu{-->
// <!--display: none;-->
// <!--margin: auto;-->
// <!--gap: 5px;-->
// <!--}-->
// <!--.nav-link{-->
// <!--display: flex;-->
// <!--}-->
// <!--.nav-link, .artwork-menu{-->
// <!--width:100%;-->
// <!--flex-direction: column;-->
// <!--align-items: center;-->
// <!--justify-content: center;-->
// <!--}-->
//
//
// <!--@media only screen and (min-width: 501px) and (max-width: 800px){-->
// <!--.bar-nav{-->
// <!--//display: block;-->
// <!--}-->
// <!--nav{-->
// <!--display: none;}-->
// <!--.nav-link {-->
// <!--display: block;-->
// <!--width: inherit;-->
// <!--}-->
// <!--.artwork-menu{justify-content: space-evenly;-->
// <!--width: 90%;-->
// <!--flex-direction: row;-->
// <!--padding: 2%;-->
// <!--display: flex;-->
// <!--}-->
//
// <!--}-->
// <!--@media only screen and (max-width: 500px){-->
// <!--nav{-->
// <!--display: none;}-->
// <!--.artwork-menu li{-->
// <!--padding-bottom: 4%;-->
// <!--}-->
//
// <!--.menu{-->
// <!--display: block;-->
// <!--cursor: pointer;-->
// <!--padding: 2%;-->
// <!--border-top: 1px slategray solid;-->
//
// <!--}}-->
// <!--</style>-->
//     <body>
//
// <article class="header-wrapper">
//     <header>
//         <div class="bar-nav"><i class="bar-nav fa-solid fa-bars"></i></div>
//         <a href="/" class="home">
//
//             <div>Jean Yves</div>
//             <img class="logo" src="../images/jeanyves_logo.png" alt="Jean Yves Logo"/>
//             <div>Hector</div>
//         </a>
//
//         <nav>
//             <li><a class="active-button" href="/artworks">Shop</a></li>
//             <li><a class="unavailable-link">Virtual Exhibition</a></li>
//             <li><a class="active-button" href="/about">About</a></li>
//             <li><a class="unavailable-link">My Story</a></li>
//             <li><a class="active-button" href="/contact">Contact</a></li>
//         </nav>
//
//         <div class="repo-icon">
//
//             <li class="user-icon-box">
//
//                 <div class="dropdown">
//                     <button style="width: 4rem; border: 1px solid black;display:  flex; align-items: center; justify-content: space-between;"
//                             type="button"
//                             class="active-button sign-user fa-regular fa-circle-user  btn btn-secondary dropdown-toggle"
//                             data-bs-toggle="dropdown">
//
//                     </button>
//                     <ul class="dropdown-menu">
//                         <li class="active-user">
//                         <li><a class="dropdown-item profile-nav" >My Information</a></li>
//                         <li><a class="dropdown-item" href="#">My Collection</a></li>
//                         <li><a class="dropdown-item active" href="#">Payment Settings</a></li>
//                         <li><a class="dropdown-item" href="#">My Reviews</a></li>
//
//             </li>
//
//
//             <li>
//                 <form action="/logout" method="post">
//                     <button class="log-user-out btn btn-light dropdown-item" type="submit">Logout</button>
//                 </form>
//             </li>
//             </ul>
//         </div>
//         </a>
//         </li>
//         <li class="anonymous-user"><a href="/login/account" class="sign-user  active-button fa-regular fa-user"></a></li>
//
//
//         <!--                <li class="user-icon-box"><a  href="/login/account" class="sign-user active-button fa-regular fa-circle-user"></a><i class="fa-solid fa-caret-down"></i></li>-->
//         <li><a href="/wishlists" class=" active-button fa-regular fa-heart"></a><i style="color: #a10404;"
//                                                                                    class="count-favorite-list favorite-count"></i>
//         </li>
//         <li><a href="/cart" class="active-button fa fa-cart-arrow-down"></a><i style="color: #a10404;"
//                                                                                class="cart-add-list cart-count"></i>
//         </li>
//         </div>
//
//
//     </header>
//     <div class="nav-link" style="position:relative;">
//         <ul class="artwork-menu">
//             <li><a class="active-button" href="/artworks">Shop</a></li>
//             <li><a class="unavailable-link">Virtual Exhibition</a></li>
//             <li><a class="active-button" href="/about">About</a></li>
//             <li><a class="unavailable-link">My Story</a></li>
//             <li><a class="active-button" href="/contact">Contact</a></li>
//         </ul>
//         <li class="menu">Menu</li>
//
//     </div>
// </article>
//
// </body>`;
//     }
// }
//
// customElements.define('header-component', Header);

import {CookieClient} from "./allcookies.js";
import {getCookie, setCookie} from "./anonymous_user_cookie.js";

const cookieClient = new CookieClient();
cookieClient.addToClientBasket("C")
cookieClient.displayNumberLike(".cart-add-list", "cart");

const cookieClient2 = new CookieClient();
cookieClient2.addToClientBasket("L");
cookieClient2.displayNumberLike(".count-favorite-list", "favorite");
// getElement(".menu").addEventListener('click', () => {
//     if (getElement(".artwork-menu").style.display === "flex") {
//         getElement(".artwork-menu").style.display = "none";
//     } else {
//         getElement(".artwork-menu").style.display = "flex";
//
//     }
// });
getElement(".log-user-out").addEventListener('click', function() {
    // This function will be executed when the button is clicked
    sessionStorage.setItem("login-username", "anonymous");

});

if(sessionStorage.getItem("login-username")==="anonymous" || sessionStorage.getItem("login-username")===null ){
    getElement(".anonymous-user").style.display="block";
    getElement(".user-icon-box").style.display="none";

}
else{
    getElement(".user-icon-box").style.display="block";
    getElement(".anonymous-user").style.display="none";

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