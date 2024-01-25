//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.model.*;
//import com.art.jeanyvesart.repository.*;
//import com.art.jeanyvesart.service.CustomerService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//
//@RequestMapping(path = "/customer", produces = "application/json")
//
//public class ACustomerController {
//    private final CustomerRepository customerRepository;
//
//
//
//    protected CustomerController(CustomerRepository customerRepository, UserDetailsManager userDetailsManager, PasswordEncoder encoder, CustomerService customerService, PasswordEncoder passwordEncoder) {
//        this.customerRepository = customerRepository;
//
//    }
//
//
//
//    @DeleteMapping("/{userId}")
//    @Transactional
//    //  @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAllCartItem(@PathVariable("userId") String id) {
//        Optional<MyCustomer> customer = customerRepository.findById(id);
//        if (customer.isPresent()) {
//            customerRepository.deleteById(id);
//        }
//    }
//
//
//    // rest of the implementation
//
//}
////
