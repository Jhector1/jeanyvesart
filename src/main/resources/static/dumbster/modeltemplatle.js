
import {Cookie, CookieClient, CookieManager, ElementManager} from "./allcookies.js";
import {checkCookie} from "./anonymous_user_cookie.js";


class Frame {

    constructor(productsBaskets) {
        this.image_id = id;
        this.children = new Frame();
        this.productsBaskets = productsBaskets;
        this.frameList = [];

    }

    imageComponent(product) {
        return `<a href="/artworks/00000${product.id}" class="image-wrapper">
                   <img class="artwork-data-img" src="${product.imageData ? `data:${product.imageData.type};base64,${product.imageData.base64Image}` : product.imageUrl}" alt="${product.title}"/>
                  </a>`
    }

    headFrame(){
        return `<section class="frame">
                    <input type="hidden" class="image-id" style="display:none;" value="1"/>`;

    }
    tailFrame(){
        return `</section>`;
    }
    likeButtonComponent(product, endpoint) {
        return ` <div>
                     ${( async ()=> {
                        const response = await fetch(`${endpoint}`);
                        (response.ok)? btnRemove(product.id, "favorite"):btnAdd(product.id, "favorite");
                        })()}
                 </div>`
    }
    addCustomComponent(html){
        return html;
    }

    addToCartButtonComponent(product, endpoint) {

        return ` <div>
                     ${( async ()=> {
                        const response = await fetch(`${endpoint}`);
                        (response.ok)? btnRemove(product.id, "cart"):btnAdd(product.id, "cart");
                    })()}
                 </div>`
    }
    soldLabel(){
        return  `<h3 class="sold-item" >Sold</h3>`;
    }

    descriptionComponent(product) {
        return `<section class="sub-title">
                    <h2 class="title">${product.title}</h2>
                    <p class="medium">${product.medium}</p>
                    <p class="size">${product.size}</p>
                    <small class="medium">${product.price}</small>                
                </section>`
    }

    addBefore(frame) {

    }

    toString(){
        return `<main>
                    <div class="link-directive">
                        <a href="/artworks" class="directive directive-artworks">artworks</a>
                        <i class="directive fa-solid fa-arrow-right-long"></i>
                        <p class="directive directive-category">Category Name</p>
                    </div>
                
                    <h1 class="artworks-category">Category Name</h1>
                
                    <article class="allArtParent">
                        <button class="parent-previous2">
                            <i class="previous2 fa-solid fa-chevron-left"></i>
                        </button>
                        
                        
                         ${ (()=>{
                             let template ='';
                             for (const product of this.productsBaskets) {
                                template+=this.headFrame();
                                template+=this.imageComponent(product.myProduct)
                                template+=`<section>
                                               ${(()=>this.descriptionComponent(product.myProduct))()}
                                               ${
                                                product.myProduct.quantity>0?(
                                                <div>
                                                    ${(() => {
                                                    return this.likeButtonComponent(product.myProduct, `${process.env.API_BASE_URL}/favorite/artworks/${checkCookie("user12345")}/${product.myProduct.id}`) +
                                                        this.addToCartButtonComponent(product.myProduct, `${process.env.API_BASE_URL}/cart/artworks/${checkCookie("user12345")}/${product.myProduct.id}`)
                                                })()
                                                }
                                                </div>
                                                ):this.soldLabel()
                                            }
                                            </section>`
                                    
                            }
                             return template + this.tailFrame();
                         })()};
                         
        }
                
                        <button class="parent-next2">
                            <i class="next2 fa-solid fa-chevron-right"></i>
                        </button>
                    </article>
                </main>`
    }

    addBetween(frame) {

    }
}


function btnRemove(id, type) {
    if (type === 'favorite') {
        return `
                            <button type="submit"  onclick="(()=>{${removeFromFavorite(id, "favorite")}; this.parentNode.innerHTML=btnAdd(${id}\, 'favorite')})()" class="liked-icon heart-circle-liked-heart"><i style="color: blue;"
                                                                                                                                                       id="liked-heart1"
                                                                                                                                                       class="liked-art fa-solid fa-heart"></i>
                            </button>
                           `
    } else if (type === 'cart') {
        return ` <div class="cart-icon cart-remove-box" onclick="(()=>{${removeFromFavorite(id,  "cart")}; this.parentNode.innerHTML=btnAdd(${id}\, 'cart')})()" >
                                                <i style="color: cornflowerblue; font-size: 1.5rem" class="fa-sharp fa-solid fa-cart-plus"></i>
                                            </div>`
    }
}

function btnAdd(id, type) {
    if (type === "favorite")
        return `
                        <button type="submit" onclick="(()=>{${addToFavorite(id, 1, 'favorite')}; this.parentNode.innerHTML=btnRemove(${id}\, 'favorite')})()"   class="not-liked-icon heart-circle-not-liked-heart"><i
                            id="not-liked-heart1"
                            class="liked-art fa-regular fa-heart"></i>
                        </button>
                   `
    else if (type === "cart") {
        return `<div class="cart-icon cart-add-box" onclick="(()=>{${addToFavorite(id, 1, "cart")}; this.parentNode.innerHTML=btnRemove(${id}\, 'cart')})()" ><i style="font-size: 1.5rem" class="fa-sharp fa-solid fa-cart-plus"></i></div>
    </div>`
    }

}


function addToFavorite(id, quantity, type) {
    return `CookieManager.addCookie(new Cookie('${type}', ${id}), ${quantity})`;
}

function removeFromFavorite(id, type) {
    return `CookieManager.removeCookie(new Cookie('${type}', ${id}))`;
}

// export default class MyTemplate {
//     constructor() {
//         this.count = 0;
//     }
//
//     // window
//     // ['handleClick']=(e)=> {
//     //     e.preventDefault();
//     //     const icon = e.target.querySelector('i');
//     //     if (icon) {
//     //         icon.classList.remove('fa-regular', 'fa-solid');
//     //         alert(90);
//     //     }
//     // };
//
//
//     async productTemplate(inventory, productsBaskets = inventory.inventories, visible = true) {
//         let template = `<main >
//                     <div class="link-directive">
//                         <a href="/artworks" class="directive directive-artworks">artworks</a>
//                         <i class="directive fa-solid fa-arrow-right-long"></i>
//                         <p class="directive directive-category" >${inventory.category}</p>
//                     </div>
//
//                     <h1 class="artworks-category" >${inventory.category}</h1>
//
//                     <article class="allArtParent">
//                         <button class="parent-previous2">
//                             <i class="previous2 fa-solid fa-chevron-left"></i>
//                         </button>`;
//
//         for (const product of productsBaskets) {
//
//
//             template += `<div  class="frame">
//                                 <input type="hidden" class="image-id" style="display:none;" value="${product.myProduct.id}">
//                                 <a href="/artworks/00000${product.myProduct.id}" class="image-wrapper">
//                                     <img class="artwork-data-img" src="${product.myProduct.imageData ? `data:${product.myProduct.imageData.type};base64,${product.myProduct.imageData.base64Image}` : product.myProduct.imageUrl}" alt="${product.myProduct.title}"/>
//                                 </a>
//                        <input type="hidden" value="${product.myProduct.quantity}" class="inventory-data"/>
//
//                                 <section class="description">
//                                     <div class="sub-title">
//                                         <p class="ellipsisText titre artwork-data">${product.myProduct.title}</p>`;
//
//             if (visible)
//                 if (product.myProduct.quantity > 0) {
//                     template += `<div class="parent-heart">`;
//
//                     const response = await fetch(`${process.env.API_BASE_URL}/favorite/artworks/${checkCookie("user12345")}/${product.myProduct.id}`);
//                     console.log(response)
//                     if (response.ok) {
//                         template += btnRemove(product.myProduct.id, "favorite");
//                     } else {
//
//                         template += btnAdd(product.myProduct.id, "favorite");
//                     }
//                     template += `</div> `
//
//                 }
//             template += `</div>
//
//                                     <section class="sub-title">
//                                         <div class="description-sub-title">
//
//
//                                             <p class="ellipsisText medium artwork-data">${product.myProduct.medium}</p>
//                                             <p  class="ellipsisText size artwork-data">${product.myProduct.size}</p>
//                                            `;
//             if (product.myProduct.quantity > 0) {
//                 template += ` <div class="price-wrapper">
//
//                                                 <small  class="currency">$US</small><small
//                                                      class="ellipsisText price artwork-data">${product.myProduct.price}</small>
//                                             </div>
//                                             <p style="display: none"  class="describe-artwork">${product.myProduct.description}</p>
//                                         </div>`
//             } else {
//                 template += `<h3 class="sold-item" >Sold</h3>`;
//             }
//
//             template += `<div style="align-self: flex-end" class="touch-mode">`
//             const response2 = await fetch(`${process.env.API_BASE_URL}/cart/artworks/${checkCookie("user12345")}/${product.myProduct.id}`);
//             if (response2.ok) {
//                 template += btnRemove(product.myProduct.id, 'cart');
//             } else {
//
//                 template += btnAdd(product.myProduct.id, 'cart');
//             }
//             // <div class="cart-icon cart-remove-box">
//             //     <i style="color: cornflowerblue; font-size: 1.5rem" class="fa-sharp fa-solid fa-cart-plus"></i>
//             // </div>
//             // <div class="cart-icon cart-add-box"><i style="font-size: 1.5rem" class="fa-sharp fa-solid fa-cart-plus"></i></div>
//             // </div>
//
//             template+=`</section>`;
//             if (visible) {
//                 template += `<div>
//
//
//                 <section class="hover-mode">
//
//                     <button class="cart-add cart-add-action">Add to Cart</button>
//                     <button class="cart-remove cart-remove-action">Remove From Cart</button>
//                 </section>
//             </div> `
//             }
//             template += `</section>
//                             </div>`;
//             this.count += 1;
//
//         }
//         template += `<button class="parent-next2">
//                     <i class="next2 fa-solid fa-chevron-right"></i></button>
//
//                 </article>
//             </main>`
//
//         return template;
//     }
//
//     get counter() {
//         return this.count;
//     }
// }
window.CookieManager = CookieManager;
window.Cookie = Cookie;
window.btnRemove = btnRemove;
window.btnAdd = btnAdd;