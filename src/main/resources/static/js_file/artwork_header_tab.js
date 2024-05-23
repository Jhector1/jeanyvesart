class ArtworkHeaderTab extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = `<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"> 
   <title></title>
    <style> 
    .header-artwork-tab-list{
        display: flex;
        position: sticky;
        top: 0;
        justify-content: center;
        gap: 20px;  
          z-index: 2;
          background-color: white;
          padding: 1rem 0;

    }
    .all-art-movement{
        position: relative;
    }
    .all-art-movement:hover .art-movement {
        display: block;
        
    }
     .all-art-movement:hover .header-artwork-tab-list {
        position: static;
        
    }
    .art-movement{
    display: none;
    //position: absolute;
    }
    </style>
    <body>
       <ul id="go-up" class="header-artwork-tab-list">
           <a href="/artworks/paintings" class="all-art-movement">PAINTINGS</a>
<!--<ul class="art-movement">&ndash;&gt;-->
<!--           <li>Ecole de la beautée</li>-->
<!--               <li>Oil</li>-->
<!--               <li>rt</li>-->
<!--               <li class="contemporary-art">Contemporary Art</li>-->
<!--               </ul></li> -->
                
           
           <a  class="unavailable-link metal-art">DRAWINGS</a>
           <a  class="unavailable-link crafts-art">CRAFTS</a>
           <a href="/artworks/clothes" class="sculpture-art">CLOTHES</a>
        </ul>
    </body>`;
    }
}

customElements.define('artwork-header-tab', ArtworkHeaderTab);