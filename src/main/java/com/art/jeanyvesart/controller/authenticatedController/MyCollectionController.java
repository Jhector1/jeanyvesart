package com.art.jeanyvesart.controller.authenticatedController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/account", produces = "application/json")
@Slf4j
public class MyCollectionController {

    @GetMapping("{sessionId}/collections")
    public String getCollectionPage(Model model, @PathVariable String sessionId, HttpServletRequest request) throws Exception {
//        if (!ProfileController.getSessionId().equals(sessionId)) {
//            throw new Exception();
//        }


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
//        Optional<MyCustomer> customer = customerRepository.findByEmail(Helper.getAuthenticatedEmail());
//        if (customer.isPresent()) {
////            Iterable<MyProduct> products = customer.get().getMyOrders().stream().map(MyOrder::getMyProducts).flatMap(List::stream).collect(Collectors.toList());
//            if(products.iterator().hasNext()) {
//                model.addAttribute("myOrders", products);
//                model.addAttribute("allArtParent", "allArtParent");
//            }
//            else{
//                model.addAttribute("allArtParent", false);
//            }
//
//        }


        return "authenticated/my-collection";
    }
}
