//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.model.CustomerFavorite;
//import com.art.jeanyvesart.model.CustomerFavoriteHelper;
//import com.art.jeanyvesart.repository.*;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(path = "/favorite/artworks", produces = "application/json")
//
//public class FavoriteController extends CustomerDataController<CustomerFavoriteHelper,CustomerFavoriteRepository, CustomerFavorite> {
//    public FavoriteController(CustomerFavoriteRepository cartArtworkRepository,
//                              ProductRepository productRepository,
//                              CustomerRepository customerRepository,
//                              InventoryRepository inventoryRepository,
//                              CustomerFavoriteHelperRepository customerFavoriteHelperRepository) {
//        super(cartArtworkRepository, productRepository, customerRepository, inventoryRepository, customerFavoriteHelperRepository);
//    }
//    private final CustomerFavoriteRepository customerFavoriteRepository;
////    private RestTemplate restTemplate = new RestTemplate();
////    private final ProductRepository productRepository;
////    private final CustomerRepository customerRepository;
////
////    public FavoriteController(CustomerFavoriteRepository cartArtworkRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
////        this.customerFavoriteRepository = cartArtworkRepository;
////        this.productRepository = productRepository;
////        this.customerRepository = customerRepository;
////    }
////
////    @GetMapping("/{userID}/{id}")
////    public ResponseEntity<CustomerFavorite> userCartByUserIdAndId(@PathVariable("userID") String userID, @PathVariable long id) {
////        Optional<CustomerFavorite> buyerCartItems = customerFavoriteRepository.findByCustomer_IdAndId(userID, id);
////        //removeBuyerCart(buyerCartItems.get());
////
////        if (buyerCartItems.isPresent()) {
////            CustomerFavorite cartItem = buyerCartItems.get();
////            return new ResponseEntity<>(cartItem, HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
////        }
////    }
////
////    @GetMapping("/{userID}")
////    public ResponseEntity<List<CustomerFavorite>> userCartById(@PathVariable("userID") String userID) {
////        List<CustomerFavorite> buyerCartItems = customerFavoriteRepository.findAllByCustomer_Id(userID);
////        if (!buyerCartItems.isEmpty()) {
////            return new ResponseEntity<>(buyerCartItems, HttpStatus.OK);
////        }
////        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
////    }
////
////    @PostMapping(value = "/save", consumes = "application/json")
////
////    @ResponseStatus(HttpStatus.CREATED)
////
////    public CustomerFavorite saVeUserCart(@RequestBody CustomerProduct cartItems) {
////        List<MyProduct> productList = new ArrayList<>(); if (customerRepository.existsById(cartItems.getCustomerId())) {
////            MyCustomer customer = customerRepository.findById(cartItems.getCustomerId()).get();
////            MyProduct product = productRepository.findById(cartItems.getProductId()).get();
////
////
////            CustomerFavorite customerFavorite;
////            if (customerFavoriteRepository.findByCustomer_Id(cartItems.getCustomerId()).isPresent()) {
////                customerFavorite = customerFavoriteRepository.findByCustomer_Id(cartItems.getCustomerId()).get();
////                customerFavorite.setMyCustomer(customer);
////                customerFavorite.getMyProducts().add(product);
////                return customerFavoriteRepository.save(customerFavorite);
////            } else {
////                customerFavorite = new CustomerFavorite();
////                customerFavorite.setMyCustomer(customer);
////                productList.add(product);
////                customerFavorite.setMyProducts(productList);
////                return customerFavoriteRepository.save(customerFavorite);
////            }
////        }
////        productList.add(productRepository.findById(cartItems.getProductId()).get());
////        return customerFavoriteRepository.save(new CustomerFavorite(new MyCustomer(cartItems.getCustomerId()), productList));
////
////    }
////
////    @Transactional
////    @DeleteMapping("/{userId}/{id}")
////    @ResponseStatus(HttpStatus.NO_CONTENT)
////    public void deleteCartItem(@PathVariable("userId") String userId, @PathVariable("id") long id) {
////        try {
////            customerFavoriteRepository.deleteByCustomer_IdAndId(userId, id);
////
////        } catch (EmptyResultDataAccessException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @DeleteMapping("/deleteAll/{userId}")
////    @Transactional
////    @ResponseStatus(HttpStatus.NO_CONTENT)
////    public CustomerFavorite deleteAllCartItem(@PathVariable("userId") String id) {
////        return customerFavoriteRepository.deleteAllByCustomer_Id(id);
////
////    }
////
////
////    @DeleteMapping("/delete-artwork/{id}")
////    @Transactional
////    @ResponseStatus(HttpStatus.NO_CONTENT)
////    public void deleteAfterPurchase(@PathVariable long id) {
////        try {
////            customerFavoriteRepository.deleteById(id);
////
////        } catch (EmptyResultDataAccessException e) {
////            e.printStackTrace();
////        }
////    }
//
////    @Bean
////    public CommandLineRunner dataLoader(
////            CartArtworkRepository cartRepo, ArtworkRepository artworkRepo) {
////        return args -> {
////
////            Artwork artwork1 = new Artwork(1, "artwork1.png", "artwork1", "$50", "Oil on Canvas", "20X20", "beautiful work", true);
////            Artwork artwork2 = new Artwork(2, "artwork2.png", "artwork2", "$60", "Oil on Canvas", "20X20", "beautiful work2", true);
////            Artwork artwork4 = new Artwork(4, "artwork4.png", "artwork4", "$50", "Oil on Canvas", "20X20", "beautiful work", true);
////            Artwork artwork3 = new Artwork(3, "artwork3.png", "artwork3", "$50", "Oil on Canvas", "20X20", "beautiful work", true);
////            UserCart userCart1 = new UserCart(1L, "1234", artwork1);
////            UserCart userCart2 = new UserCart(2L, "7235", artwork2);
////            UserCart userCart3 = new UserCart(3L, "1234", artwork3);
////            UserCart userCart4 = new UserCart(4L, "1234", artwork4);
////
////
////
////            artworkRepo.save(artwork1);
////            artworkRepo.save(artwork2);
////            artworkRepo.save(artwork3);
////            artworkRepo.save(artwork4);
////            cartRepo.save(userCart1);
////            cartRepo.save(userCart4);
////            cartRepo.save(userCart3);
////
////            cartRepo.save(userCart2);
////
////        };
////    }
//}
////
