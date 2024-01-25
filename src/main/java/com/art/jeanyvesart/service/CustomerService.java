package com.art.jeanyvesart.service;

import com.art.jeanyvesart.exceptionHandler.UserAlreadyExistException;
import com.art.jeanyvesart.dto.CustomerDto;
import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.MyCustomer;
import com.art.jeanyvesart.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class CustomerService implements IUserService<ResponseEntity<?>> {
    private final Consumer<MyCustomer> consumer;

    private final PasswordEncoder passwordEncoder;

    public CustomerService(Consumer<MyCustomer> consumer, PasswordEncoder passwordEncoder) {
        this.consumer = consumer;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> registerNewCustomerAccount(CustomerDto customerDto) throws UserAlreadyExistException {
        if (emailExists(customerDto.getEmail())) {
            throw new UserAlreadyExistException("An account is already existed with that email address: " + customerDto.getEmail());
        }
        MyCustomer myCustomer = new MyCustomer();
        myCustomer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        myCustomer.setEmail(customerDto.getEmail());
        myCustomer.setFullName(customerDto.getFullName());
        consumer.createResource("/customer/account/save", myCustomer);
        return new ResponseEntity<>(HttpStatus.CREATED);

        // the rest of the registration operation
    }

    private boolean emailExists(String email) {
        return consumer.getResourceById("/customer/account/{email}", Objects.requireNonNull(email), MyCustomer.class).isPresent();
    }
}