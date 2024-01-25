package com.art.jeanyvesart.controller;


import com.art.jeanyvesart.helper.Helper;
import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.CustomerFavorite;
import com.art.jeanyvesart.model.MyCustomer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
@Slf4j
@ControllerAdvice
@Controller
@RequestMapping("/account")
@SessionAttributes({"sessionId"})
public class ProfileController {
    private final Consumer<MyCustomer> consumer;

    public ProfileController(Consumer<MyCustomer> consumer) {
        this.consumer = consumer;
    }


    @GetMapping("/{sessionId}/profile")
    public String getProfilePage(Model model, HttpServletResponse response){
        //        Optional<MyCustomer> customer = customerRepository.findByEmail(Helper.getAuthenticatedEmail());


        Optional<MyCustomer> customer = consumer.getResourceById("/customer/account/{email}", Objects.requireNonNull(Helper.getAuthenticatedEmail()),MyCustomer.class);
       customer.ifPresent(myCustomer ->{
           model.addAttribute("secret", myCustomer.getId());
           model.addAttribute("fullName", (customer.get().getFullName()==null)?"Anonymous": customer.get().getFullName());
       });



        model.addAttribute( "sessionId" ,getSessionId());
        Helper.setCookieValue("session_Id", getSessionId(), response);

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
    @ModelAttribute
    public static  String getSessionId(){
       return Helper.getSessionId();
    }

}
