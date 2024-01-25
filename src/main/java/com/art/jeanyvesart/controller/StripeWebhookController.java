//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.component.MyResourceManager;
//import com.art.jeanyvesart.dto.MyOrderDto;
//import com.art.jeanyvesart.model.*;
//import com.art.jeanyvesart.repository.*;
//import com.stripe.Stripe;
//import com.stripe.exception.SignatureVerificationException;
//import com.stripe.exception.StripeException;
//import com.stripe.model.*;
//import com.stripe.model.Product;
//import com.stripe.model.checkout.Session;
//import com.stripe.net.Webhook;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(produces = "application/json")
//@Slf4j
//public class StripeWebhookController {
//    final
//    CustomerRepository customerRepository;
//    final
//    InventoryRepository inventoryRepository;
//    final
//    CustomerCartHelperRepository customerCartHelperRepository;
//    final
//    CustomerFavoriteHelperRepository favoriteHelperRepository;
//    final
//    ProductRepository productRepository;
//    final
//    CustomerCartRepository customerCartRepository;
//    @Value("${stripe.webhook.secret}")
//    String endpointSecret;
//    @Value("${stripe.secret.key}")
//    private String secretKey;
//
//    public StripeWebhookController(CustomerRepository customerRepository, InventoryRepository inventoryRepository,
//                                   CustomerCartHelperRepository cartArtworkRepository,
//                                   CustomerFavoriteHelperRepository favoriteArtworkRepository,
//                                   ProductRepository productRepository, CustomerCartRepository customerCartRepository) {
//        this.customerRepository = customerRepository;
//        this.inventoryRepository = inventoryRepository;
//        this.customerCartHelperRepository = cartArtworkRepository;
//        this.favoriteHelperRepository = favoriteArtworkRepository;
//        this.productRepository = productRepository;
//        this.customerCartRepository = customerCartRepository;
//    }
//
//    @PostConstruct
//    public void init() {
//        Stripe.apiKey = secretKey;
//    }
//
//    @PostMapping("/stripe/webhook/event")
//    public <H> ResponseEntity<String> handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader, HttpServletRequest request) throws StripeException {
//        if (sigHeader == null) {
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//        }
//        Event event;
//
//        try {
//            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
//
//        } catch (SignatureVerificationException s) {
//            log.info("error: {}","Webhook error while validating signature");
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        // Deserialize the nested object inside the event
//        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
//        StripeObject stripeObject = null;
//        if (dataObjectDeserializer.getObject().isPresent()) {
//            stripeObject = dataObjectDeserializer.getObject().get();
//        } else {
//            // Deserialization failed, probably due to an API version mismatch.
//            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
//            // instructions on how to handle this case, or return an error here.
//        }
//        //log.info(payload);
//        // Handle the event
//        switch (event.getType()) {
////            case "payment_intent.created":
////                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
////                log.info("Payment for {} succeeded", paymentIntent.getAmount());
////                // Then define and call a method to handle the successful payment intent.
////                // handlePaymentIntentSucceeded(paymentIntent);
////                break;
//
//            case "checkout.session.completed":
//                Session session = (Session) stripeObject; //event.getDataObjectDeserializer().getObject().get();
//
//                LineItemCollection displayItems = session.listLineItems();
//                //  log.info("artwork quantity {}",               displayItems.getData());
//                List<MyProduct> myProducts = new ArrayList<>();
//                String customerId = null;
//
//                if (displayItems != null) {
//                    for (LineItem lineItemsDatum : displayItems.getData()) {
//                        Product product = Product.retrieve(lineItemsDatum.getPrice().getProduct());
//                        String artworkId = product.getMetadata().get("artworkId"); //Id that reference the buyercart object
//
//                        customerId = product.getMetadata().get("customerId");
//                        String inventoryId = "00000" + artworkId; //Id that reference the buyercart object
//                        Optional<Inventory> inventoryToUpdateOptional = inventoryRepository.findById(inventoryId);
//                        if (inventoryToUpdateOptional.isPresent()) {
//                            Inventory inventory = new Inventory();
//
//
//                            Inventory inventoryToUpdate = inventoryToUpdateOptional.get();
//                            inventory.setQuantity(inventoryToUpdate.getQuantity() - Math.toIntExact(lineItemsDatum.getQuantity()));
//                            // Forward the data to a PosT endpoint
//                            String patchEndpoint = MyResourceManager.updateEndpoint("/data/artworks/" + inventoryId);
//                            String deleteFavoriteEndpoint = MyResourceManager.updateEndpoint("/favorite/artworks/delete-artwork/{id}");
//
//                            String deleteCartEndpoint = MyResourceManager.updateEndpoint("/cart/artworks/delete-artwork/{id}");
//                            Optional<MyProduct> myProduct = productRepository.findById(Long.valueOf(artworkId));
//                            myProduct.ifPresent(myProducts::add);
//                            if (inventory.getQuantity() <= 0) {
//
////                                CustomerProduct customerProduct = new CustomerProduct();
////                                customerProduct.setCustomerId(customerId);
////                                customerProduct.setProductId(inventoryToUpdate.getMyProduct().getId());
//                                MyResourceManager.manageUserData(favoriteHelperRepository, deleteFavoriteEndpoint, artworkId);
//                            }
//                            MyResourceManager.updateInventoryWithPatch(inventory, patchEndpoint);
//
//                            MyResourceManager.manageUserData(customerCartHelperRepository, deleteCartEndpoint, artworkId);
//
////
////
////                            List<CustomerCartHelper> customers = (List<CustomerCartHelper>) cartArtworkRepository.findAll();
////                            if (customers.stream().anyMatch(customerCartHelper -> customerCartHelper.getMyProduct().getId() == Long.parseLong(artworkId)))
////
////                                MyResourceManager.deleteCustomerData(deleteCartEndpoint, artworkId);
//                        }
//
//
//                    }
//                    String orderPostEndpoint = MyResourceManager.updateEndpoint("/api/artworks/purchase/order");
//
//                    MyOrderDto myOrderDto = new MyOrderDto();
//                    myOrderDto.setCustomerId(customerId);
//
//
//                    myOrderDto.setProducts(myProducts);
//                    MyResourceManager.createOrder(myOrderDto, orderPostEndpoint);
//
//
//                }
//                //log.info("MyProduct for {} created", lineItemsData);
//
//                // Then define and call a method to handle the successful payment intent.
//                // handlePaymentIntentSucceeded(paymentIntent);
//                break;
//
//
//            default:
//                log.warn("Unhandled event type: {}", event.getType());
//        }
//        return new ResponseEntity<>("event created", HttpStatus.OK);
//    }
//
//
//}
