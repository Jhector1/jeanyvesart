const loader = document.querySelector(".loading");
function my_loader() {
    window.addEventListener("load", () => {
        loader.classList.add("loader--hidden");
        loader.addEventListener("transitionend", () => {
            loader.remove();
        })
    });
}
export {my_loader};
