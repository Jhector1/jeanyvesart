const loader = document.querySelector(".loading");

window.addEventListener("load", () => {
    loader.classList.add("loader--hidden");
    loader.addEventListener("transitionend", () => {
        loader.remove();
    })
});
