package com.art.jeanyvesart.controller;

import com.art.jeanyvesart.component.EndPointPool;
import com.art.jeanyvesart.helper.Helper;
import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/cart", produces = "application/json")
@SessionAttributes({"stripePublicKey"})

public class CartController {
    @Value("${stripe.public.key}")
    private String stripePublicKey;
    private Helper helper;
    private final Consumer<CustomerFavorite> consumer;

    public CartController(Helper helper, Consumer<CustomerFavorite> consumer) {
        this.helper = helper;
        this.consumer = consumer;
    }


    @GetMapping
    public String getCartPage(Model model, HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//
//        if (cookies != null) {
//            // Iterate through the cookies
//            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getName() + " " + cookie.getValue());
//            }
//        }
        //System.out.println(Helper.getCookieValue(request, "user12345"));
        String userId = helper.getCookieValue( "user12345");
        if (userId != null) {
            Optional<CustomerFavorite> customerFavorite = consumer.getResourceById("/favorite/artworks/cart/{userId}",Objects.requireNonNull(userId),CustomerFavorite.class);
            if (customerFavorite.isPresent()) {
                CustomerFavorite favoriteArtwork = customerFavorite.get();
                List<MyProduct> products = favoriteArtwork.getCustomerDataHelpers().stream().map(CustomerDataHelper::getMyProduct).collect(Collectors.toList());
                model.addAttribute("allArtParent", "allArtParent");
System.out.println(customerFavorite);
                model.addAttribute("favoriteProducts", products);
            }
        }
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("userCartEndpoint", EndPointPool.getMap().get("userCartEndpoint"));

        return "cart_page";
    }

    @ModelAttribute("stripePublicKey")
    public String getStripePublicKey() {

        return stripePublicKey;
    }


    @GetMapping("/artworks/checkout/success")
    public String successPage(@RequestParam String session_id, Model model) {
        model.addAttribute("session_id", session_id);

        return "success_page";
    }

    @GetMapping("/artworks/checkout/cancel")
    public String cancelPage(@RequestParam String session_id, Model model) {
        model.addAttribute("session_id", session_id);

        return "error_page";
    }

}
//
