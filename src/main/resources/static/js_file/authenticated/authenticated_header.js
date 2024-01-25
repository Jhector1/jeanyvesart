import {getAllElement, getElement} from "../helperMethod.js";

// getElement(".payment-setting").onclick=()=>{
//     getAllElement(".profile-info").forEach(data=>{
//     data.style.display ="none";
//     getElement(".card-update").style.display = "block";
// })};
// getElement(".user-info-header").onclick=()=>{
//     getAllElement(".profile-info").forEach(data=>{
//         data.style.display ="none";
//         getElement(".user-info").style.display = "block";
//     })};
// Function to be executed on window resize
function handleResize() {
    // Get the current viewport width
    const viewportWidth = window.innerWidth || document.documentElement.clientWidth;

    // Check if the viewport width is 600 pixels or less
    if (viewportWidth <= 580) {
        console.log("Viewport is 600 pixels or less!");
        getElement(".nav-profile-child").classList.add("offcanvas");
        getElement(".header-menu").style.display="none";
        // Add your code here to handle the condition
        getElement(".nav-profile-child").classList.add("offcanvas-start");

    } else {
        getElement(".header-menu").style.display="flex";

        getElement(".nav-profile-child").classList.remove("offcanvas");
        // Add your code here to handle the condition
        getElement(".nav-profile-child").classList.remove("offcanvas-start");
        getElement(".nav-profile-child").classList.remove("offcanvas-start");

        console.log("Viewport is greater than 600 pixels.");
        // Add your code here for the case when the viewport is greater than 600 pixels
    }
}

// Attach the handleResize function to the window's resize event
window.addEventListener("resize", handleResize);

// Call handleResize initially to check the viewport width on page load
handleResize();
