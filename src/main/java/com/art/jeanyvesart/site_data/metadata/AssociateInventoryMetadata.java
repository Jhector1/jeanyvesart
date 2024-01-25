package com.art.jeanyvesart.site_data.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssociateInventoryMetadata {
    private final static Map<Integer, InventoryMetadata> mapImages = new HashMap<>();

    public static void addToMapImages(List<?> elements) {
        List<String> images = new ArrayList<>();


        String baseUrl = "https://jeanyveshector.com";
        images.add(baseUrl + "/images/paintings/healing_plants/healing0.webp");
        images.add(baseUrl + "/images/paintings/healing_plants/healing1.webp");
        images.add(baseUrl + "/images/paintings/healing_plants/healing2.webp");
        images.add("/images/paintings/healing_plants/healing0details/healing0detail0.webp");
        images.add("/images/paintings/healing_plants/healing0details/healing0detail1.webp");
        for (int i = 0; i < elements.size(); i++) {
            mapImages.put(i, new InventoryMetadata());
        }
        mapImages.put(2, new InventoryMetadata(0));

        mapImages.put(5, new InventoryMetadata(0));
        mapImages.put(6, new InventoryMetadata(images));
        mapImages.put(7, new InventoryMetadata(images));
        mapImages.put(8, new InventoryMetadata(images));
        mapImages.put(9, new InventoryMetadata(0));
        mapImages.put(12, new InventoryMetadata(0));

        mapImages.put(15, new InventoryMetadata(0));
        mapImages.put(17, new InventoryMetadata(0));
        mapImages.put(28, new InventoryMetadata(0));
        mapImages.put(29, new InventoryMetadata(0));
        mapImages.put(32, new InventoryMetadata(0));
        mapImages.put(34, new InventoryMetadata(0));

        mapImages.put(35, new InventoryMetadata(6));
        mapImages.put(36, new InventoryMetadata(6));
        mapImages.put(37, new InventoryMetadata(6));
        mapImages.put(38, new InventoryMetadata(6));
        mapImages.put(39, new InventoryMetadata(6));
        mapImages.put(40, new InventoryMetadata(6));
        mapImages.put(41, new InventoryMetadata(6));
        mapImages.put(42, new InventoryMetadata(6));
        mapImages.put(43, new InventoryMetadata(6));
        mapImages.put(44, new InventoryMetadata(6));


    }

    public static Map<Integer, InventoryMetadata> getMapImages() {

        return mapImages;
    }
}
