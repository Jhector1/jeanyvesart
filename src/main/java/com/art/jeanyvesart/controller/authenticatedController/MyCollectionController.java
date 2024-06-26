package com.art.jeanyvesart.controller.authenticatedController;

import com.art.jeanyvesart.helper.Helper;
import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.MyCustomer;
import com.art.jeanyvesart.model.MyProduct;
import com.art.jeanyvesart.model.MyOrder;

import java.util.*;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/account", produces = "application/json")
@Slf4j
public class MyCollectionController {
    @Value("${baseUrl}")
    private String sever_domain;

    private static String baseUrl;
private Helper helper;
    @PostConstruct
    public void init() {
        baseUrl = sever_domain;
    }

    private final Consumer<MyCustomer> consumer;

    public MyCollectionController(Helper helper, Consumer<MyCustomer> consumer) {
        this.helper = helper;
        this.consumer = consumer;
    }

    @GetMapping("{sessionId}/collections")
    public String getCollectionPage(Model model, @PathVariable String sessionId, HttpServletRequest request) throws Exception {
//        if (!ProfileController.getSessionId().equals(sessionId)) {
//            throw new Exception();
//        }


//
//        Cookie[] cookies = request.getCookies();
//        String userId = null;
//        // Check if cookies exist
//        if (cookies != null) {
//
//            for (Cookie cookie : cookies) {
//                // Check if the current cookie has the name "useriod"
//                if ("user12345".equals(cookie.getName())) {
//                    // Get the value of the "useriod" cookie
//                     userId = cookie.getValue();
//
//                    // Do something with the "useriod" cookie value
//                    log.info("Value of user Id cookie: {}" , userId);
//                }
//            }
//        } else {
//           log.error("No cookies found in the request.");
//        }
//        Optional<MyCustomer> customer = consumer.getResourceById("/customer/account/{email}", Objects.requireNonNull(Helper.getAuthenticatedEmail()),MyCustomer.class);
//log.info("customer {}", customer.get().getEmail());
////        Optional<MyCustomer> customer = customerRepository.findByEmail(Helper.getAuthenticatedEmail());
//        if (customer.isPresent()) {
//            Iterable<MyProduct> products = customer.get().getMyOrders().stream().map(MyOrder::getMyProducts).flatMap(List::stream).collect(Collectors.toList());
//            if(products.iterator().hasNext()) {
//                model.addAttribute("myOrders", products);
//                model.addAttribute("allArtParent", "allArtParent");
//            }
//            else{
//                model.addAttribute("allArtParent", false);
//            }
//
//        }

        getMyOrders(model, request);
        return "authenticated/my-collection";
    }

    public  void getMyOrders(Model model, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        log.info("userinfo , {}",Objects.requireNonNull(helper.getCookieValue( "user12345") ));
        ResponseEntity<List<MyOrder>> response = restTemplate.exchange(
                baseUrl+"//order/customer/" + Objects.requireNonNull(helper.getCookieValue( "user12345")),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MyOrder>>() {
                }

        );
        // Check if the request was successful (status code 200)
        if (response.getStatusCode().is2xxSuccessful()) {
            Iterable<MyProduct> products = response.getBody().stream().map(MyOrder::getMyProducts).flatMap(List::stream).collect(Collectors.toList());
            if (products.iterator().hasNext()) {
                model.addAttribute("myOrders", products);
                model.addAttribute("allArtParent", "allArtParent");
            } else {
                model.addAttribute("allArtParent", false);
            }

        }
        // Check if the request was successful (status code 200)
        log.info("my orders, {}", response.getBody());

    }
}
