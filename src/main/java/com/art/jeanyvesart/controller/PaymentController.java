package com.art.jeanyvesart.controller;//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.service.EmailServiceImpl;
//import com.art.jeanyvesart.dto.ChargeRequest;
//import com.art.jeanyvesart.dto.CreatePayment;
//import com.art.jeanyvesart.model.Artwork;
//import com.art.jeanyvesart.model.Checkout;
//import com.art.jeanyvesart.repository.AddressRepository;
//import com.art.jeanyvesart.repository.ArtworkRepository;
//import com.art.jeanyvesart.repository.BuyerRepository;
//import com.art.jeanyvesart.repository.CheckoutRepository;
//import com.stripe.Stripe;
//import com.stripe.exception.*;
//import com.stripe.model.Charge;
//import com.stripe.model.PaymentIntent;
//import com.stripe.param.ChargeCreateParams;
//import com.stripe.param.CustomerCreateParams;
//import com.stripe.param.PaymentIntentCreateParams;
//import jakarta.annotation.PostConstruct;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@RestController
//public class PaymentController {
//    private final
//    AddressRepository addressRepository;
//    private final CheckoutRepository checkoutRepository;
//    private final ArtworkRepository artworkRepository;
//    private final BuyerRepository buyerRepository;
//    @Value("${stripe.secret.key}")
//    private String secretKey;
//    private final
//    EmailServiceImpl emailService;
//    public PaymentController(CheckoutRepository checkoutRepository,
//                             ArtworkRepository artworkRepository,
//                             AddressRepository addressRepository,
//                             BuyerRepository buyerRepository, EmailServiceImpl emailService) {
//        this.addressRepository = addressRepository;
//        this.checkoutRepository = checkoutRepository;
//        this.artworkRepository = artworkRepository;
//        this.buyerRepository = buyerRepository;
//
//
//        this.emailService = emailService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Stripe.apiKey = secretKey;
//    }
//
//    @CrossOrigin(origins = {"http://localhost:8080/", "https://jeanyveshector.up.railway.app/", "https://jeanyveshector.com/"})
//    @PostMapping("/charge")
//    public ResponseEntity<String> charge(@RequestBody ChargeRequest request, Errors error, Model model) throws StripeException {
//        if (error.hasErrors()) {
//            model.addAttribute("errorAlert", "It seems you have entered some invalid information " +
//                    ", please carefully review the form " +
//                    "and resubmit. If the error persists, please contact me.");
//            return new ResponseEntity<>("payment_processing", HttpStatus.BAD_REQUEST);
//        }
//        // System.out.println(request);
//
//        try {
//            Map<String, Object> params = new HashMap<>();
//            params.put("amount", (long) (request.getCreatePayment().getAmount() * 100L));
//            params.put("currency", "usd");
//            params.put("description", "Payment for Jean Yves Art");
//            params.put("confirm", true);
//            Map<String, Object> paymentMethodData = new HashMap<>();
//            paymentMethodData.put("type", "card");
//            paymentMethodData.put("card[token]", request.getCreatePayment().getToken());
//            params.put("payment_method_data", paymentMethodData);
//
//            PaymentIntent paymentIntent = PaymentIntent.create(params);
//            params.put("receipt_email", request.getCheckoutInfo().getBuyer().getEmail());
//            System.out.println("success");
//            model.addAttribute("success", "Payment successful. Charge ID: " + paymentIntent.getId());
//
//            // Handle card errors
//            model.addAttribute("error", "error cant process payment");
//            this.addressRepository.save(request.getCheckoutInfo().getBuyer().getAddress());
//            this.artworkRepository.saveAll(request.getCheckoutInfo().getArtworks());
//            this.buyerRepository.save(request.getCheckoutInfo().getBuyer());
//            this.checkoutRepository.save(request.getCheckoutInfo());
//
//
//            // return new ResponseEntity<>("yeah", HttpStatus.OK);
//emailService.sendSimpleMessage("myart@jeanyveshector.com", "sygmalink@gmail.com", "Someone make a purchase", "Congratulations Someone purchase one of your artworks");
//            return new ResponseEntity<>(paymentIntent.toJson(), HttpStatus.OK);
//        }catch (CardException e) {
//            Charge charge = Charge.retrieve(e.getStripeError()
//                    .getPaymentIntent()
//                    .getLatestCharge());
//            if(charge
//                    .getOutcome()
//                    .getType().equals("blocked")) {
//                System.out.println("Payment blocked for suspected fraud.");
//                return new ResponseEntity<>("Payment blocked for suspected fraud.", HttpStatus.BAD_GATEWAY);
//
//            } else if(e.getCode().equals("card_declined") && (e.getDeclineCode().equals("insufficient_funds"))) {
//                System.out.println("Declined by the issuer.");
//                return new ResponseEntity<>("Insufficient funds, declined by the issuer.", HttpStatus.PAYMENT_REQUIRED);
//
//            } else if(e.getCode().equals("expired_card")) {
//                System.out.println("Card expired.");
//                return new ResponseEntity<>("Card expired.", HttpStatus.BAD_GATEWAY);
//
//            } else {
//                System.out.println("Other card error.");
//                return new ResponseEntity<>("Create PaymentIntent failed", HttpStatus.BAD_GATEWAY);
//
//            }
//        } catch (Exception e) {
//            System.out.println("Another problem occurred, maybe unrelated to Stripe.");
//            return new ResponseEntity<>("Another problem occurred, maybe unrelated to Stripe.", HttpStatus.BAD_GATEWAY);
//
//        }
//    }
//}
//
//
