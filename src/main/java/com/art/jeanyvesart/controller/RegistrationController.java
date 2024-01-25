package com.art.jeanyvesart.controller;

import com.art.jeanyvesart.dto.CustomerDto;
import com.art.jeanyvesart.exceptionHandler.UserAlreadyExistException;
import com.art.jeanyvesart.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@SessionAttributes({"customerDto"})
public class RegistrationController {
    private final CustomerService customerService;

    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String register() {
        return "registrationForm";
    }
    @ModelAttribute(name ="customerDto")
    public CustomerDto customerDto(){
        return  new CustomerDto();
    }
    @PostMapping("/customer/register")
    public ResponseEntity<?> registerUserAccount(@RequestBody @Valid CustomerDto customerDto, Errors error) {
        if (error.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors: "  + error.getFieldError().getDefaultMessage());

        }
        try {
            return customerService.registerNewCustomerAccount(customerDto);
        } catch (UserAlreadyExistException uaeEx) {
            return new ResponseEntity<>(uaeEx.getMessage(), HttpStatus.CONFLICT);
        }
//        Optional<MyCustomer> optionalMyCustomer = customerRepository.findByEmail(customer.getUsername());
//        if (optionalMyCustomer.isEmpty()) {
////            UserDetails user = User.withDefaultPasswordEncoder()
////                    .username(customer.getUsername())
////                    .password(customer.getPassword())
////                    .roles("USER") // Assign roles as needed
////                    .build();
//            System.out.println(customer + " Customer created");
//            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
//           // userDetailsManager.createUser(user);
//            customerRepository.save(customer);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } else return new ResponseEntity<>(HttpStatus.CONFLICT);

    }


}
