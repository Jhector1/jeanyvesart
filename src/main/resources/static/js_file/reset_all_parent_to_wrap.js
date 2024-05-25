import {getAllElement, getElement} from "./helperMethod.js";


const allArtParent= getAllElement(".allArtParent");
const allNext2= getAllElement(".parent-next2");
const allPrevious2 = getAllElement(".parent-previous2");

for(let box=0; box<allArtParent.length; box++){
    allArtParent[box].style.flexWrap="wrap";
    allNext2[box].style.display="none";
    allPrevious2[box].style.display="none";
    allArtParent[box].style.justifyContent="center";

}
getElement(".link-directive").style.display="flex";
