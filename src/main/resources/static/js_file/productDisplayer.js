// import displayProduct from "./artwork_like.js";
// import {getAllElement, getElement} from "./helperMethod.js";
//
// class ProductElement extends HTMLElement {
//     constructor() {
//         super();
//         // Initialize properties
//         this._category = '';
//
//     }
//
//     // Define observed attributes
//     static get observedAttributes() {
//         return ['category'];
//     }
//
//     // Attribute change callback
//     attributeChangedCallback(name, oldValue, newValue) {
//         if (oldValue !== newValue) {
//             this[name] = newValue;
//         }
//     }
//
//     // Connected callback
//     connectedCallback() {
//         this.render();
//     }
//
//     // Property getters and setters
//     get category() {
//         return this._category;
//     }
//
//     set category(value) {
//         this._category = value;
//         this.render();
//     }
//
//
//     // Render method
//     render() {
//         this.innerHTML = `
//            <article>
//
// <header  th:replace="~{header.html :: header-component}" >
// </header>
// <!--<artwork-header-tab></artwork-header-tab>-->
// <div th:replace="~{dryTemplate.html :: spinner(message='One second...', dynamicClass='loader')}"></div>
//
// <div>
//
// </div>
// <article class="artworks-wrapper">
//
// </article>
// <footer-component></footer-component>
// <script type="module">
//
//   displayProduct(this._category).then(()=>{
//
//
//
//     const allArtParent= getAllElement(".allArtParent");
//     const allNext2= getAllElement(".parent-next2");
//     const allPrevious2 = getAllElement(".parent-previous2");
//
//     for(let box=0; box<allArtParent.length; box++){
//       allArtParent[box].style.flexWrap="wrap";
//       allNext2[box].style.display="none";
//       allPrevious2[box].style.display="none";
//       allArtParent[box].style.justifyContent="center";
//
//     }
//     getElement(".link-directive").style.display="flex";
//   });
// </script>
// </article>
//
//         `;
//     }
// }
//
// customElements.define('product-element', ProductElement);
