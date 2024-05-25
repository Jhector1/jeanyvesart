export default class MyTemplate {
    constructor() {
        this.count = 0;
    }

    productTemplate(inventory) {
        let template = `<main >
                    <div class="link-directive">
                        <a href="/artworks" class="directive directive-artworks">artworks</a>
                        <i class="directive fa-solid fa-arrow-right-long"></i>
                        <p class="directive directive-category" >${inventory.category}</p>
                    </div>

                    <h1 class="artworks-category" >${inventory.category}</h1>

                    <article class="allArtParent">
                        <button class="parent-previous2">
                            <i class="previous2 fa-solid fa-chevron-left"></i>
                        </button>`;

        inventory.inventories.forEach(product => {


            template += `<div  class="frame">
                                <input type="hidden" class="image-id" style="display:none;" value="${product.myProduct.id}">
                                <a href="/artworks/00000${product.myProduct.id}" class="image-wrapper">
                                    <img class="artwork-data-img" src="${product.myProduct.imageUrl}" alt="${product.myProduct.title}"/>
                                </a>
                                <section class="description">
                                    <div class="sub-title">
                                        <p class="ellipsisText titre artwork-data">${product.myProduct.title}</p>
                                        <div class="parent-heart">
                                            <div class="liked-icon-form">
                                                <button type="submit" class="liked-icon heart-circle-liked-heart"><i style="color: blue;"
                                                                                                       id="liked-heart1"
                                                                                                       class="liked-art fa-solid fa-heart"></i>
                                                </button>
                                            </div>
                                            <div class="not-liked-icon-form">
                                                <button type="submit" class="not-liked-icon heart-circle-not-liked-heart"><i id="not-liked-heart1"
                                                                                                               class="liked-art fa-regular fa-heart"></i>
                                                </button>
                                            </div>
                                        </div>
               
                                    </div>
                    
                                    <section class="sub-title">
                                        <div class="description-sub-title">
                                         
                                            <p class="ellipsisText medium artwork-data">${product.myProduct.medium}</p>
                                            <p  class="ellipsisText size artwork-data">${product.myProduct.size}</p>
                                            <div class="price-wrapper">
                                                <small  class="currency">$US</small><small
                                                     class="ellipsisText price artwork-data">${product.myProduct.price}</small>
                                            </div>
                                            <p style="display: none"  class="describe-artwork">${product.myProduct.description}</p>
                                        </div>
                                        <div style="align-self: flex-end" class="touch-mode">
                                            <div class="cart-icon cart-remove-box">
                                                <i style="color: cornflowerblue; font-size: 1.5rem" class="fa-sharp fa-solid fa-cart-plus"></i>
                                            </div>
                                            <div class="cart-icon cart-add-box"><i style="font-size: 1.5rem" class="fa-sharp fa-solid fa-cart-plus"></i></div>
                                        </div>
                                    </section>
                                    <h3 class="sold-item">Sold</h3>
                    
                                    <section class="hover-mode">
                                        <button class="cart-add cart-add-action">Add to Cart</button>
                                        <button class="cart-remove cart-remove-action">Remove From Cart</button>
                                    </section>
                                </section>
                            </div>`;
            this.count += 1;

        })
        template += `<button class="parent-next2">
                    <i class="next2 fa-solid fa-chevron-right"></i></button>

                </article>
            </main>`

        return template;
    }

    get counter() {
        return this.count;
    }
}