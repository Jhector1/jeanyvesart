// import {getAllElement, getElement} from "./helperMethod.js";
//
// (() => {
//     const shareButton = getAllElement(".share-to-media");
//     const favoriteId = getAllElement(".image-id");
//     const favoriteTitle = getAllElement(".share-title")
//    // const favoriteImage = getAllElement(".favorite-img")
//
//     for (let y = 0; y < shareButton.length; y++) {
//         shareButton[y].onclick = (e) => {
//             getElement(".social-media-link-cover").style.display = "flex";
//             let element_id = favoriteId[y].innerHTML;
//             const msg = encodeURIComponent("Check out this artwork by Jean Yves Hector");
//             let artwork_title = favoriteTitle[y].innerHTML;
//             console.log(element_id + artwork_title)
//           //  const imageLink = favoriteImage[y].src;
//             const title = encodeURIComponent(artwork_title);
//             const link = encodeURI("https://jeanyveshector.com/artworks/00000" + element_id);
//             getElement(".facebook").href = `https://www.facebook.com/share.php?u=${link}`;
//             getElement(".twitter").href = `https://www.twitter.com/share?&url=${link}&text=${msg}&hashtags=jeanyveshector,art,haiti,green,artist`;
//             getElement(".linkedin").href = `https://www.linkedin.com/sharing/share-offsite/?url=${link}`;
//             getElement(".pinterest").href = `https://www.pinterest.com/pin/create/button/?url=${link}&description=${artwork_title}&method=button`;
//
//             const body = encodeURIComponent("You should see this artwork by the Haitian artist Jean Yves Hector titled " + artwork_title + "\n");
//             getElement(".envelope").href = `mailto:?subject=${msg}&body=${body + link}`;
//
//         }
//
//     }
// getElement("#share-cancel").onclick=()=>getElement(".social-media-link-cover").style.display="none";
// })();

import {getAllElement, getElement} from "./helperMethod.js";

function isTouchDevice() {
    return 'ontouchstart' in window || navigator.maxTouchPoints;
}

if (isTouchDevice()) {
    console.log('The device is touch-enabled.');
} else {
    console.log('The device does not have touch capabilities.');
}


export function sharing(imageTitle, imageId, shareButton) {
    // const shareButton = getAllElement(".share-to-media");
   // const favoriteId = getAllElement(".image-id");
    //const favoriteTitle = getAllElement(".share-title")
    // const favoriteImage = getAllElement(".favorite-img")
    shareButton.onclick = (e) => {
        console.log(imageId);
        const msg = encodeURIComponent("Check out this artwork by Jean Yves Hector");
        const msg_touch = ("Check out this artwork by Jean Yves Hector");

       // let artwork_title = favoriteTitle[y].innerHTML;
        //  const imageLink = favoriteImage[y].src;
      //  const title = encodeURIComponent(artwork_title);
        const link = encodeURI("https://jeanyveshector.com/artworks/00000" + imageId);

        const body = encodeURIComponent("You should see this artwork by the Haitian artist Jean Yves Hector titled " + imageTitle + "\n");
        const body_touch = ("You should see this artwork by the Haitian artist Jean Yves Hector titled " + imageTitle + "\n");

        // Check if the Web Share API is supported by the browser

        // const shareButton = document.getElementById('share-button');

        // Add a click event listener to the share button
        function isTouchDevice() {
            return Modernizr.touch;

        }

        if (isTouchDevice() && navigator.share) {
            navigator.share({
                title: msg_touch,
                text: body_touch,
                url: link
            }).then(() => console.log('Shared successfully.'))
                .catch((error) => console.log('Error sharing:', error));
        } else {
            getElement(".social-media-link-cover").style.display = "flex";
            getElement(".facebook").href = `https://www.facebook.com/share.php?u=${link}`;
            getElement(".twitter").href = `https://www.twitter.com/share?&url=${link}&text=${msg}&hashtags=jeanyveshector,art,haiti,green,artist`;
            getElement(".linkedin").href = `https://www.linkedin.com/sharing/share-offsite/?url=${link}`;
            getElement(".pinterest").href = `https://www.pinterest.com/pin/create/button/?url=${link}&description=${imageTitle}&method=button`;
            getElement(".envelope").href = `mailto:?subject=${msg}&body=${body + link}`;

        }
    }


    getElement("#share-cancel").onclick = () => getElement(".social-media-link-cover").style.display = "none";
}