package com.art.jeanyvesart.controller;


import com.art.jeanyvesart.dto.CustomerDto;
import com.art.jeanyvesart.helper.Helper;
import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.Address;
import com.art.jeanyvesart.model.MyCustomer;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@ControllerAdvice
@Controller
@RequestMapping("/account")
@SessionAttributes({"sessionId"})
public class ProfileController {
    private Helper helper;
    private final Consumer<MyCustomer> consumer;

    public ProfileController(Helper helper, Consumer<MyCustomer> consumer) {
        this.helper = helper;
        this.consumer = consumer;
    }

    @GetMapping("/{sessionId}/home")
    public String getProfileHomePage(Model model, HttpServletResponse response){
        //        Optional<MyCustomer> customer = customerRepository.findByEmail(Helper.getAuthenticatedEmail());


        Optional<MyCustomer> customer = consumer.getResourceById("/customer/account/{email}", Objects.requireNonNull(helper.getAuthenticatedEmail()),MyCustomer.class);
        customer.ifPresent(myCustomer ->{
            log.info("customer profile  triggered {}", customer.get().getMyOrders());

            CustomerDto customerDto = new CustomerDto();
            customerDto.setEmail(myCustomer.getEmail());
            customerDto.setFullName(myCustomer.getFullName());
            customerDto.setTelephone(myCustomer.getTelephone());
            Set<Address> addressSet = myCustomer.getAddressList();
            customerDto.setAddress(!addressSet.isEmpty()? (Address) addressSet.toArray()[0] : null);

            model.addAttribute("secret", myCustomer.getId());
            model.addAttribute("customerDto", customerDto);
        });




        model.addAttribute( "sessionId" ,helper.getSessionId());
        helper.setCookieValue("session_Id", helper.getSessionId());



        return "authenticated/userHome";

    }

    @GetMapping("/{sessionId}/profile")
    public String getProfilePage(Model model, HttpServletResponse response){
        //        Optional<MyCustomer> customer = customerRepository.findByEmail(Helper.getAuthenticatedEmail());


        Optional<MyCustomer> customer = consumer.getResourceById("/customer/account/{email}", Objects.requireNonNull(helper.getAuthenticatedEmail()),MyCustomer.class);
       customer.ifPresent(myCustomer ->{
           log.info("customer profile  triggered {}", customer.get().getMyOrders());

           CustomerDto customerDto = new CustomerDto();
           customerDto.setEmail(myCustomer.getEmail());
           customerDto.setFullName(myCustomer.getFullName());
           customerDto.setTelephone(myCustomer.getTelephone());
           Set<Address> addressSet = myCustomer.getAddressList();
           customerDto.setAddress(!addressSet.isEmpty()? (Address) addressSet.toArray()[0] : null);

           model.addAttribute("secret", myCustomer.getId());
           model.addAttribute("customerDto", customerDto);
       });




        model.addAttribute( "sessionId" ,helper.getSessionId());
        helper.setCookieValue("session_Id", helper.getSessionId());

//        System.out.println("details: "+a.getDetails());
     // System.out.println("principal:  "+a.getPrincipal());
//        Cookie myCookie = new Cookie("my-session-id", sessionId);
//
//        // Set the cookie's max age (in seconds)
//        myCookie.setMaxAge(36000); // 1 hour (you can adjust this as needed)
//
//        // Set the cookie path (optional)
//        myCookie.setPath("/");
//
//        // Add the cookie to the response
//        response.addCookie(myCookie);

        return "authenticated/userProfile";

    }


}
