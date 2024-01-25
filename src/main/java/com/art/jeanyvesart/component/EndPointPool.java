package com.art.jeanyvesart.component;

import java.util.HashMap;
import java.util.Map;

public class EndPointPool {
    private static Map<String, String> map = new HashMap<>();

    public static void addEndPoint() {
        map.put("userCartEndpoint","/cart/artworks" );
     map.put("userFavoriteEndpoint","/favorite/artworks" );
       map.put("userCheckoutEndpoint","/cart/checkout/cart/checkout");
        map.put("userEmailSenderEndpoint","/sendemail");
       map.put("allArtworkEndpoint","/data/artworks");
        map.put("purchaseArtworkEndpoint","/api/artworks");

    }

    public static Map<String, String> getMap() {
        addEndPoint();
        return map;
    }
}
