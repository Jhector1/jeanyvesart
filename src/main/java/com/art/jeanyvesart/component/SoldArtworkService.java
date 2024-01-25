package com.art.jeanyvesart.component;//package com.art.jeanyvesart.component;
//
//import com.art.jeanyvesart.model.Artwork;
//import com.art.jeanyvesart.model.MyCustomer;
//import com.art.jeanyvesart.model.MyOrder;
//import com.art.jeanyvesart.model.MyProduct;
//import com.art.jeanyvesart.repository.ArtworkRepository;
//import com.art.jeanyvesart.repository.OrderRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class SoldArtworkService {
//    String description = "Description";
//    String paintingCategory = "painting";
//    boolean unique= true;
//
//    String priceUnavailable = "Unavailable";
//
//
//    public static String getRootPath() {
//        //String scheme = request.getScheme();
//        // String serverName = request.getServerName();
//        //int portNumber = request.getServerPort();
////        return scheme + "://" + serverName + ":" + portNumber;
//        return "https://jeanyveshector.com";
//    }
//
//    @Bean
//    public CommandLineRunner artworkPurchased(OrderRepository repo) {
//        List<MyProduct>  products = new ArrayList<>();
//        return args -> {
//            products.add( new Artwork(29, getRootPath() + "/images/paintings/renaissance/renaissance9.webp",
//                    "The Fruit of Resilience", priceUnavailable,
//                    "Oil on canvas", "8x8inch", description, 1
//            ));
//            products.add(new Artwork(28, getRootPath() + "/images/paintings/renaissance/renaissance8.webp",
//                    "Rafael and Michael", priceUnavailable,
//                    "Oil on canvas", "126x80inch", description,1
//            ));
//            products.add(new Artwork(5, getRootPath() + "/images/paintings/enlightenment/enlightenment5.webp",
//                    "Enlighten", priceUnavailable,
//                    "Oil and Acrylic on canvas", "24x24inch", description,1
//            ));
//            products.add(new Artwork(9, getRootPath() + "/images/paintings/healing_plants/healing3.webp",
//                    "Blossom Season", priceUnavailable,
//                    "Oil on canvas", "20x20inch", description,1
//            ));
//            products.add(new Artwork(12, getRootPath() + "/images/paintings/healing_plants/healing6.webp",
//                    "Moon Trees", priceUnavailable,
//                    "Oil and Acrylic on canvas", "12x12inch", description,1
//            ));
//            products.add(new Artwork(34, getRootPath() + "/images/clothes/jacket/jacket4.webp",
//                    "Jacket Beni", priceUnavailable,
//                    "Acrylic on clothe", "Medium", description,1
//            ));
//            products.add(new Artwork(15, getRootPath() + "/images/paintings/little_black_angel/angel1.webp",
//                    "Queen Brigitte", priceUnavailable,
//                    "Oil on canvas", "18x20inch", description,1
//            ));
//            products.add(new Artwork(17L, getRootPath() + "/images/paintings/little_black_angel/angel3.webp",
//                    "Oh dear", priceUnavailable,
//                    "Oil on canvas", "20x20inch", description, 1
//            ));
//            products.add(  new Artwork(32L,getRootPath()+"/images/clothes/jacket/jacket2.webp",
//                    "Jacket Beni", priceUnavailable,
//                    "","Medium", description,1
//            ));
//            repo.save(new MyOrder( new Artwork(32L,getRootPath()+"/images/clothes/jacket/jacket2.webp",
//                    "Jacket Beni", priceUnavailable,
//                    "","Medium", description,1
//            )));
//        };
//    }
//}