//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.dto.MyOrderDto;
//import com.art.jeanyvesart.model.Inventory;
//import com.art.jeanyvesart.model.MyCustomer;
//import com.art.jeanyvesart.model.MyOrder;
//import com.art.jeanyvesart.model.MyProduct;
//import com.art.jeanyvesart.repository.CustomerRepository;
//import com.art.jeanyvesart.repository.InventoryRepository;
//import com.art.jeanyvesart.repository.OrderRepository;
//import com.art.jeanyvesart.repository.ProductRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "/api/artworks", produces = "application/json")
//public class PurchaseArtworkController {
//    private final
//    OrderRepository orderRepository;
//    private final
//    ProductRepository productRepository;
//    private final
//    InventoryRepository inventoryRepository;
//    private final CustomerRepository customerRepository;
//
//    public PurchaseArtworkController(OrderRepository repository, ProductRepository productRepository, InventoryRepository inventoryRepository, CustomerRepository customerRepository) {
//        this.orderRepository = repository;
//        this.productRepository = productRepository;
//        this.inventoryRepository = inventoryRepository;
//        this.customerRepository = customerRepository;
//    }
//
//    @GetMapping("/purchase")
//    public List<MyProduct> artPurchase() {
//        List<MyOrder> orders = (List<MyOrder>) orderRepository.findAll();
//        List<MyProduct> myProducts = new ArrayList<>();
//        for (MyOrder order : orders) {
//            myProducts.addAll(order.getMyProducts());
//        }
//        return myProducts;
//
//    }
//@Transactional
//    @PostMapping("/purchase/order")
//    public MyOrder order(@RequestBody MyOrderDto myOrderDto) {
////        MyOrder newOrder = new MyOrder();
////        Optional<MyProduct> optionalProduct = productRepository.findById(cartItems.getProductId());
////        if (optionalProduct.isPresent()) {
////            MyProduct myProduct = optionalProduct.get();
////            Optional<MyCustomer> optionalCustomer = customerRepository.findById(cartItems.getCustomerId());
////
////            if (optionalCustomer.isPresent()) {
////                MyCustomer myCustomer = optionalCustomer.get();
////
////
////                MyOrder myOrder;
////                Optional<MyOrder> myOrderOptional = orderRepository.findByMyCustomer_Id(cartItems.getCustomerId());
////                myOrder = myOrderOptional.orElseGet(MyOrder::new);
////                myOrder.setMyCustomer(myCustomer);
////
////                myOrder.getMyProducts().add(myProduct);
////                return orderRepository.save(myOrder);
////            }
////
////
////
////            newOrder.setMyCustomer(new MyCustomer(cartItems.getCustomerId()));
////
////            newOrder.getMyProducts().add(myProduct);
//     //   }
//
//
//
//
////        MyOrder currentOrder = new MyOrder();
////        MyCustomer myCustomer = new MyCustomer(myOrder.getMyCustomer().getId());
////        Optional<MyOrder> myOrderOptional = orderRepository.findByMyCustomer_Id(myOrder.getMyCustomer().getId());
////        Optional<MyCustomer> myCustomerOptional = customerRepository.findById(myOrder.getMyCustomer().getId());
////
////
////        if (myCustomerOptional.isPresent()) {
////
////            if (myOrderOptional.isPresent()) {
////                myOrderOptional.get().getMyProducts().addAll(myOrder.getMyProducts());
////
////            } else {
////                currentOrder.setMyProducts(myOrder.getMyProducts());
////                currentOrder.setMyCustomer(myCustomerOptional.get());
////                return orderRepository.save(currentOrder);
////            }
////
////        }
////
////        currentOrder.setMyProducts(myOrder.getMyProducts());
////        currentOrder.setMyCustomer(myCustomer);
////       customerRepository.save(myCustomer);
////        return orderRepository.save(currentOrder);
//    Optional<MyCustomer> myCustomerOptional = customerRepository.findById(myOrderDto.getCustomerId());
//    MyOrder myOrder = new MyOrder();
//    if(myCustomerOptional.isPresent()){
//        myOrder.setMyCustomer(myCustomerOptional.get());
//    }
//    else{
//        myOrder.setMyCustomer(new MyCustomer(myOrderDto.getCustomerId()));
//
//    }
//    for(MyProduct myProduct: myOrderDto.getProducts()){
//        Optional<MyProduct> myProductOptional = productRepository.findById(myProduct.getId());
//        myProductOptional.ifPresent(product -> myOrder.getMyProducts().add(product));
//    }
//        //myOrder.setMyProducts(myOrderDto.getProducts());
//
//        return orderRepository.save(myOrder);
//       // return null;
//
//    }
//
//
//    @GetMapping("/purchase/{id}")
//    public ResponseEntity<Inventory> artPurchase(@PathVariable long id) {
//       Optional<Inventory> inventoryOptional = inventoryRepository.findByIdAndQuantityEquals("00000"+id, 0);
//        return inventoryOptional.map(inventory -> new ResponseEntity<>(inventory, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
//    }
////    @PutMapping(value = "/artwork_purchased/{id}", consumes = "application/json")
////    public ResponseEntity<?> updateArtworkById(@RequestBody Artwork artwork, @PathVariable long id) {
////        try {
////                Optional<MyOrder> artworkToUpdate = repository.findById(id);
////                if (artworkToUpdate.isPresent()) {
////                    MyOrder updateArtwork = artworkToUpdate.get();
////
////                    // Update only the specified fields
////
////                    updateArtwork.setImageUrl(artwork.getImageUrl());
////
////
////                    updateArtwork.setDescription(artwork.getDescription());
////
////                    updateArtwork.setSize(artwork.getSize());
////
////                    updateArtwork.setMedium(artwork.getMedium());
////                    updateArtwork.setInventory(updateArtwork.getInventory() - artwork.getQuantity());
////                    if (updateArtwork.getInventory() <= 0) {
////                        updateArtwork.setPrice("Unavailable");
////                        updateArtwork.setPurchase(true);
////
////                    }
////                    repository.save(updateArtwork);
////
////                }
////
////            return ResponseEntity.ok(artwork);
////        } catch (Exception e) {
////            return ResponseEntity.notFound().build();
////        }
////    }
//
////    @PutMapping(value = "/artwork_purchased", consumes = "application/json")
////    public ResponseEntity<?> updateArtwork(@RequestBody ProductList artworkList) {
////        try {
////            for (BuyerCart data : artworkList.getCartArtworkList()) {
////                Optional<Artwork> artwork = repository.findById(Math.toIntExact(data.getId()));
////                if (artwork.isPresent()) {
////                    Artwork updateArtwork = artwork.get();
////
////                    // Update only the specified fields
////
////                    updateArtwork.setImageUrl(data.getImageUrl());
////
////
////                    updateArtwork.setDescription(data.getDescription());
////
////                    updateArtwork.setSize(data.getSize());
////
////                    updateArtwork.setMedium(data.getMedium());
////                    updateArtwork.setInventory(updateArtwork.getInventory() - data.getQuantity());
////                    updateArtwork.setPurchase(true);
////                    if (updateArtwork.getInventory() <= 0) {
////                        updateArtwork.setPrice("Unavailable");
////                        repository.save(updateArtwork);
////                    }
////
////                }
////            }
////            return ResponseEntity.ok(artworkList);
////        } catch (Exception e) {
////            return ResponseEntity.notFound().build();
////        }
////    }
//
//    // ...
//
//}