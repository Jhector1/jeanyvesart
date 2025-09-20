import {
    getAllElement,
    likeOrNotDesign,
    slideNextPrevious,
} from "./helperMethod.js";
import MyTemplate from "./artworkTemplate.js";

export default function displayProduct(category) {
    const myTemplate = new MyTemplate();
    const wrapper = document.querySelector(".artworks-wrapper");
    if (!wrapper) return Promise.resolve(0);

    const endpoint = `${apiBaseUrl}/data/artworks/category/${
        category === "all" ? "all" : encodeURIComponent(category)
    }`;

    return fetch(endpoint)
        .then((res) => {
            if (!res.ok) throw new Error(`Fetch failed: ${res.status}`);
            return res.json();
        })
        .then((data) => {
            if (!Array.isArray(data) || data.length === 0) return myTemplate.counter;

            // Build once, insert once (way faster than += in a loop)
            const chunks = [];
            if (category === "all") {
                for (const inventory of data) {
                    chunks.push(
                        `<div class="artworks-grandparent">` +
                        myTemplate.productTemplate(inventory) +
                        `<div class="see-more">
                  <div class="see-in-details-wrapper">
                    <hr>
                    <h1 class="see-in-details" href="#">See More</h1>
                    <hr>
                  </div>
                  <i style="display:none" id="down-id" class="down fa-solid fa-chevron-down"></i>
               </div>
            </div>`
                    );
                }
            } else {
                for (const inventory of data) {
                    chunks.push(myTemplate.productTemplate(inventory));
                }
            }
            wrapper.insertAdjacentHTML("beforeend", chunks.join(""));

            // Make all images lazy (no template change needed)
            wrapper
                .querySelectorAll("img:not([loading])")
                .forEach((img) => img.setAttribute("loading", "lazy"));

            return myTemplate.counter;
        })
        .then((count) => {
            // Cache NodeLists once
            const frames = wrapper.querySelectorAll(".frame");
            const imageIds = wrapper.querySelectorAll(".image-id");
            const likedHearts = wrapper.querySelectorAll(".liked-icon");
            const notLikedHearts = wrapper.querySelectorAll(".not-liked-icon");
            const soldEls = wrapper.querySelectorAll(".sold-item");
            const descriptions = wrapper.querySelectorAll(".describe-artwork");

            const n = Math.min(
                count,
                frames.length,
                imageIds.length,
                likedHearts.length,
                notLikedHearts.length,
                soldEls.length,
                descriptions.length
            );

            for (let i = 0; i < n; i++) {
                const frame = frames[i];
                const meta = frame ? frame.querySelectorAll(".artwork-data") : null;
                const img = frame ? frame.querySelector(".artwork-data-img") : null;
                if (!meta || !img) continue;

                const art = new Map();
                art.set("id", imageIds[i].value);
                // (Preserving your original keys/values)
                art.set("date", img.src);
                art.set("imageUrl", img.src);
                art.set("title", meta[0]?.textContent?.trim() ?? "");
                art.set("medium", meta[1]?.textContent?.trim() ?? "");
                art.set("size", meta[2]?.textContent?.trim() ?? "");
                art.set("price", meta[3]?.textContent?.trim() ?? "");
                art.set("description", descriptions[i]?.textContent?.trim() ?? "");

                likeOrNotDesign(
                    imageIds[i].value,
                    notLikedHearts[i],
                    likedHearts[i],
                    art,
                    soldEls[i]
                );
            }
        })
        .then(() => {
            slideNextPrevious();

            // SEE MORE: event delegation (one listener, fast, handles dynamic items)
            const directiveEls = getAllElement(".link-directive");
            const gpEls = wrapper.querySelectorAll(".artworks-grandparent");
            const categoryEls = getAllElement(".artworks-category");
            const parentEls = getAllElement(".allArtParent");
            const seeMoreEls = wrapper.querySelectorAll(".see-more");

            if (seeMoreEls.length > 0) {
                wrapper.addEventListener(
                    "click",
                    (e) => {
                        const btn = e.target.closest?.(".see-in-details");
                        if (!btn) return;

                        const buttons = Array.from(
                            wrapper.querySelectorAll(".see-in-details")
                        );
                        const idx = buttons.indexOf(btn);
                        if (idx < 0) return;

                        // Hide all collections quickly
                        gpEls.forEach((el) => (el.style.display = "none"));
                        seeMoreEls.forEach((el) => (el.style.display = "none"));
                        categoryEls.forEach((el) => (el.style.display = "none"));

                        // Show the selected collection
                        directiveEls[idx] && (directiveEls[idx].style.display = "flex");
                        gpEls[idx] && (gpEls[idx].style.display = "flex");
                        parentEls[idx] && (parentEls[idx].style.display = "flex");
                        parentEls[idx] && (parentEls[idx].style.flexWrap = "wrap");
                        parentEls[idx] && (parentEls[idx].style.justifyContent = "center");
                        categoryEls[idx] && (categoryEls[idx].style.display = "block");

                        const nextBtn = getAllElement(".parent-next2")[idx];
                        const prevBtn = getAllElement(".parent-previous2")[idx];
                        nextBtn && (nextBtn.style.display = "none");
                        prevBtn && (prevBtn.style.display = "none");

                        window.scrollTo({ top: 0, behavior: "smooth" });
                    },
                    { passive: true }
                );
            }
        })
        .catch((err) => {
            console.error(err);
            return 0;
        });
}
