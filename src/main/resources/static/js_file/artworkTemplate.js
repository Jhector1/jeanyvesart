export default class MyTemplate {
    constructor() {
        this.count = 0;
    }

    productTemplate(inventory) {
        const framesHtml = inventory.inventories
            .map((product) => {
                const p = product.myProduct;
                return `
          <div class="frame" data-product-id="${p.id}">
            <input type="hidden" class="image-id" style="display:none;" value="${p.id}">
            <a href="/artworks/00000${p.id}" class="image-wrapper">
              <img
                class="artwork-data-img"
                src="${p.imageUrl}"
                alt="${p.title}"
                loading="lazy"
                decoding="async"
              />
            </a>

            <section class="description">
              <div class="sub-title">
                <p class="ellipsisText titre artwork-data">${p.title}</p>

                <div class="parent-heart">
                  <div class="liked-icon-form">
                    <button type="button" class="liked-icon heart-circle-liked-heart" aria-label="Unlike">
                      <i class="liked-art fa-solid fa-heart" style="color: blue;"></i>
                    </button>
                  </div>
                  <div class="not-liked-icon-form">
                    <button type="button" class="not-liked-icon heart-circle-not-liked-heart" aria-label="Like">
                      <i class="liked-art fa-regular fa-heart"></i>
                    </button>
                  </div>
                </div>
              </div>

              <section class="sub-title">
                <div class="description-sub-title">
                  <p class="ellipsisText medium artwork-data">${p.medium}</p>
                  <p class="ellipsisText size artwork-data">${p.size}</p>
                  <div class="price-wrapper">
                    <small class="currency">$US</small>
                    <small class="ellipsisText price artwork-data">${p.price}</small>
                  </div>
                  <p class="describe-artwork" style="display:none;">${p.description}</p>
                </div>

                <div class="touch-mode" style="align-self:flex-end">
                  <div class="cart-icon cart-remove-box" aria-label="Remove from cart">
                    <i class="fa-sharp fa-solid fa-cart-plus" style="font-size:1.5rem; color:cornflowerblue;"></i>
                  </div>
                  <div class="cart-icon cart-add-box" aria-label="Add to cart">
                    <i class="fa-sharp fa-solid fa-cart-plus" style="font-size:1.5rem"></i>
                  </div>
                </div>
              </section>

              <h3 class="sold-item">Sold</h3>

              <section class="hover-mode">
                <button type="button" class="cart-add cart-add-action">Add to Cart</button>
                <button type="button" class="cart-remove cart-remove-action">Remove From Cart</button>
              </section>
            </section>
          </div>
        `;
            })
            .join("");

        this.count += inventory.inventories.length;

        return `
      <main>
        <div class="link-directive">
          <a href="/artworks" class="directive directive-artworks">artworks</a>
          <i class="directive fa-solid fa-arrow-right-long"></i>
          <p class="directive directive-category">${inventory.category}</p>
        </div>

        <h1 class="artworks-category">${inventory.category}</h1>

        <article class="allArtParent">
          <button class="parent-previous2" type="button" aria-label="Previous">
            <i class="previous2 fa-solid fa-chevron-left"></i>
          </button>

          ${framesHtml}

          <button class="parent-next2" type="button" aria-label="Next">
            <i class="next2 fa-solid fa-chevron-right"></i>
          </button>
        </article>
      </main>
    `;
    }

    get counter() {
        return this.count;
    }
}
