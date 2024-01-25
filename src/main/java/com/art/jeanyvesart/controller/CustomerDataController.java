//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.dto.CustomerProduct;
//import com.art.jeanyvesart.helper.ProductAndInventory;
//import com.art.jeanyvesart.model.*;
//import com.art.jeanyvesart.repository.*;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//public abstract class CustomerDataController<H extends CustomerDataHelper, R extends CustomerDataRepository<H, M>, M extends CustomerData<H>> {
//    private final R repository;
//    private final InventoryRepository inventoryRepository;
//    private final CustomerDataHelperRepository<H> customerDataHelperRepository;
//    private final ProductRepository productRepository;
//    private final CustomerRepository customerRepository;
//
//    public CustomerDataController(R repository, ProductRepository productRepository, CustomerRepository customerRepository, InventoryRepository inventoryRepository, CustomerDataHelperRepository<H> customerDataHelperRepository) {
//        this.repository = repository;
//        this.inventoryRepository = inventoryRepository;
//        this.productRepository = productRepository;
//        this.customerRepository = customerRepository;
//        this.customerDataHelperRepository = customerDataHelperRepository;
//    }
//
//
//    @GetMapping("/product_id/{userID}")
//    public ResponseEntity<List<Long>> getUserDataByUserId(@PathVariable("userID") String userID) {
//        Optional<M> items = repository.findByMyCustomer_Id(userID);
//
//        if (items.isPresent()) {
//            List<H> products = items.get().getCustomerDataHelpers();
//            // MyResourceManager.removeExpiredUserData(products);
//            List<Long> listId = products.stream().map(product -> product.getMyProduct().getId()).collect(Collectors.toList());
//            return new ResponseEntity<>(listId, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
//
//}
//
//
//    @GetMapping("/{userID}/{id}")
//    public ResponseEntity<MyProduct> userCartByUserIdAndId(@PathVariable("userID") String userID, @PathVariable long id) {
//        Optional<M> items = repository.findByMyCustomer_Id(userID);
//        // MyResourceManager.removeExpiredUser(userID);
//        //removeBuyerCart(buyerCartItems.get());
//
//
//        if (items.isPresent()) {
//            List<H> products = items.get().getCustomerDataHelpers();
//            // MyResourceManager.removeExpiredUserData(products);
//            if (products.stream().anyMatch(product -> product.getMyProduct().getId() == id)) {
//                return new ResponseEntity<>(productRepository.findById(id).get(), HttpStatus.OK);
//            }
//        }
//
//        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//
//    }
//
//
//    @GetMapping("/{userID}")
//    public ResponseEntity<List<ProductAndInventory>> userCartById(@PathVariable("userID") String userID) {
//        Optional<M> item = repository.findByMyCustomer_Id(userID);
//        if (item.isPresent()) {
//            List<H> products = item.get().getCustomerDataHelpers();
//            if (products.size() > 0) {
//                return new ResponseEntity<>(products.stream()
//                        .map(product ->
//                                new ProductAndInventory(inventoryRepository.findById("00000" + product.getMyProduct().getId()).get().getQuantity(), product.getMyProduct(), product.getQuantity()))
//                        .collect(Collectors.toList())
//                        , HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//    }
//
//    @PostMapping(value = "/save", consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public M saVeUserCart(@RequestBody CustomerProduct cartItems, M data, H helper) {
//        // helper.setId(cartItems.getProductId());
//        try {
//            if (data instanceof CustomerCart) {
//
//                helper.setQuantity(cartItems.getQuantity());
//            }
//            Optional<MyProduct> optionalProduct = productRepository.findById(cartItems.getProductId());
//            if (optionalProduct.isPresent()) {
//                MyProduct myProduct = optionalProduct.get();
//                Optional<MyCustomer> optionalCustomer = customerRepository.findById(cartItems.getCustomerId());
//
//                if (optionalCustomer.isPresent()) {
//                    MyCustomer myCustomer = optionalCustomer.get();
//
//
//                    M modelData;
//                    Optional<M> modelDataInstance = repository.findByMyCustomer_Id(cartItems.getCustomerId());
//                    modelData = modelDataInstance.orElse(data);
//                    modelData.setMyCustomer(myCustomer);
//                    helper.setMyProduct(myProduct);
//                    customerDataHelperRepository.save(helper);
//                    modelData.getCustomerDataHelpers().add(helper);
//                    return repository.save(modelData);
//                }
//
//
//                helper.setMyProduct(myProduct);
//                data.setMyCustomer(new MyCustomer(cartItems.getCustomerId()));
//                customerDataHelperRepository.save(helper);
//                data.getCustomerDataHelpers().add(helper);
//            }
//            return repository.save(data);
//        } catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }
//
//    }
//
//    @Transactional
//    @DeleteMapping("/{userId}/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteCartItem(@PathVariable("userId") String userId, @PathVariable("id") long id) {
//        try {
//            Optional<M> data = repository.findByMyCustomer_Id(userId);
//            if (data.isPresent()) {
//                List<H> products = data.get().getCustomerDataHelpers();
//                for (Iterator<H> iterator = products.iterator(); iterator.hasNext(); ) {
//                    H helper = iterator.next();
//                    if (productRepository.findById(id).get().getId() == helper.getMyProduct().getId()) {
//                        iterator.remove();
//                        customerDataHelperRepository.delete(helper);
//                    }
//                }
//                //customerDataHelperRepository.deleteByProductId(id);
//
//
//            }
//            // MyResourceManager.removeExpiredUserData(buyerCartOptional.get(), "cart");
//
//        } catch (EmptyResultDataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @DeleteMapping("/deleteAll/{userId}")
//    @Transactional
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAllCartItem(@PathVariable("userId") String id) {
//        Optional<M> data = repository.findByMyCustomer_Id(id);
//        if (data.isPresent()) {
//            repository.deleteAllByMyCustomer_Id(id);
//        }
//    }
//
//
//    @DeleteMapping("/delete-artwork/{id}")
//    @Transactional
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAfterPurchase(@PathVariable long id) {
//        try {
//
//            Iterable<M> data = repository.findAll();
//            for (M cart : data) {
//                List<H> products = cart.getCustomerDataHelpers();
//                for (Iterator<H> iterator = products.iterator(); iterator.hasNext(); ) {
//                    H helper = iterator.next();
//                    if (productRepository.findById(id).get().getId() == helper.getMyProduct().getId()) {
//                        iterator.remove();
//                        customerDataHelperRepository.delete(helper);
//                    }
//                }
//                //customerDataHelperRepository.deleteByProductId(id);
//
//
//            }
//        } catch (EmptyResultDataAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
////
