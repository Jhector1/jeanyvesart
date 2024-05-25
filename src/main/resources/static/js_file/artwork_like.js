import {
    cartAddRemoveDesign, cartAddRemoveDesignIcon,
    getAllElement,
    getElement,
    likeOrNotDesign,
    slideNextPrevious,
    SlideshowIndex, visitArtwork
} from "./helperMethod.js";
import {Cookie, ElementManager} from "./allcookies.js";
import productTemplate from "./artworkTemplate.js";
import MyTemplate from "./artworkTemplate.js";

export default function displayProduct(category) {

    const myTemplate = new MyTemplate();

   return (category ==='all' ?  fetch(`${apiBaseUrl}/data/artworks/category/all`).then(response => response.json()).then(data => {

        data.forEach(inventory => {

             let template = `<div class="artworks-grandparent">`
            template += myTemplate.productTemplate(inventory);
         template+= ` <div class="see-more">
                <div class="see-in-details-wrapper">
                    <hr>
                    <h1 class="see-in-details" href="#">See More</h1>
                
                    <hr>
                </div>
                <i style="display: none" id="down-id" class="down fa-solid fa-chevron-down"></i>
            </div>
        </div>`;

            document.querySelector(".artworks-wrapper").innerHTML += template;


        })

        return myTemplate.counter;


    }): fetch(`${apiBaseUrl}/data/artworks/category/${category}`).then(response => response.json()).then(data => {
      data.forEach(inventory => {


          let template = myTemplate.productTemplate(inventory);


          document.querySelector(".artworks-wrapper").innerHTML += template;


      })

      return myTemplate.counter;


  })).then((count) => {
        const all_frame = getAllElement(".frame");

        const cookieNum = getAllElement(".image-id");
        const liked_heart = getAllElement(".liked-icon");
        const not_liked_heart = getAllElement(".not-liked-icon");

        const elementSold = getAllElement(".sold-item");

        const artworkID = getAllElement(".image-id");
        let all_artwork_data;


        for (let y = 0; y < count; y++) {

            const all_artwork = all_frame[y].querySelectorAll(".artwork-data");
            const img_url = all_frame[y].querySelector(".artwork-data-img");

            all_artwork_data = new Map();
            all_artwork_data.set("id", artworkID[y].value);
            all_artwork_data.set("date", img_url.src);

            all_artwork_data.set("imageUrl", img_url.src);
            all_artwork_data.set("title", all_artwork[0].innerHTML);
            all_artwork_data.set("medium", all_artwork[1].innerHTML);
            all_artwork_data.set("size", all_artwork[2].innerHTML);
            all_artwork_data.set("price", all_artwork[3].innerHTML);
            //all_artwork_data.set("unique", unique[i].value);
            //all_artwork_data.set("inventory", getAllElement(".inventory-data")[i]);
            all_artwork_data.set("description", getAllElement(".describe-artwork")[y].innerHTML);
            likeOrNotDesign(cookieNum[y].value, not_liked_heart[y], liked_heart[y], all_artwork_data, elementSold[y]);

        }
    }).then(() => {
        slideNextPrevious();
        const seeMore = getAllElement(".see-more");
        const directive = getAllElement(".link-directive");
        const seeInDetails = getAllElement(".see-in-details");
        for (let x = 0; x < seeMore.length; x++) {
            seeInDetails[x].onclick = () => {
                // document.body.innerHTML+=`<div th:replace="~{dryTemplate.html :: spinner(message='One second...', dynamicClass='loader')}"></div>`;
                // const loader = document.querySelector(".loading");
                // loader.classList.remove("loading-checkout");
                // loader.classList.add("loader");
                for (let t = 0; t < seeMore.length; t++) {
                    getAllElement(".artworks-grandparent")[t].style.display = "none";
                    seeMore[t].style.display = "none";

                    getAllElement(".artworks-category")[t].style.display = "none";

                }
                directive[x].style.display = "flex";
                getAllElement(".artworks-grandparent")[x].style.display = "flex";
                getAllElement(".allArtParent")[x].style.display = "flex";
                getAllElement(".artworks-category")[x].style.display = "block";

                getAllElement(".allArtParent")[x].style.flexWrap = "wrap";
                seeMore[x].style.display = "none";
                getAllElement(".parent-next2")[x].style.display = "none";
                getAllElement(".parent-previous2")[x].style.display = "none";
                getAllElement(".allArtParent")[x].style.justifyContent = "center";


                window.scrollTo(0, 0);


            }
        }
    })
}

