//package com.art.jeanyvesart.helper;
//
//import com.art.jeanyvesart.model.Inventory;
//import com.art.jeanyvesart.model.MyProduct;
//import com.art.jeanyvesart.site_data.metadata.AssociateInventoryMetadata;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.art.jeanyvesart.site_data.metadata.AssociateInventoryMetadata.getMapImages;
//
//public class InventoryManager {
//    private final static  Map<String , List< MyProduct>> productPool = new HashMap<>();
//    //private static int count=0;
//    private static List<MyProduct> productList= new ArrayList<>();
//    private static final List<Inventory> inventoryList = new ArrayList<>();
//    public static void initProductList(List<MyProduct> productList){
//        InventoryManager.productList = productList;
//        AssociateInventoryMetadata.addToMapImages(productList);
//
//    }
//    public static void  addToInventoryList(int count){
//
//        MyProduct myProduct = productList.get(count);
//        String category  = getCategory(myProduct.getImageUrl());
//        Inventory inventory = new Inventory("00000"+myProduct.getId(),
//                myProduct,category ,
//                getMapImages().get(count).getQuantity(), getMapImages().get(count).getImages() );
//        if(productPool.containsKey(category)){
//            productPool.get(category).add(myProduct);
//        }else{
//            List<MyProduct> newProductStock = new ArrayList<>();
//            newProductStock.add(myProduct);
//            productPool.put(category, newProductStock);
//
//        }
//        inventoryList.add(inventory);
//
//    }
//
//    public static Map<String, List<MyProduct>> getProductPool() {
//        return productPool;
//    }
//
//    public static List<Inventory> getInventoryList() {
//       // addToInventoryList();
//        return inventoryList;
//    }
//    private static String getCategory(String imageUrl) {
//        if (imageUrl.toLowerCase().trim().contains("paintings")) {
//            return "Paintings";
//        } else if (imageUrl.toLowerCase().trim().contains("clothes")) {
//            return "Clothes";
//        } else if (imageUrl.toLowerCase().trim().contains("print")) {
//            return "Print";
//        } else {
//            return "";
//        }
//    }
//}
