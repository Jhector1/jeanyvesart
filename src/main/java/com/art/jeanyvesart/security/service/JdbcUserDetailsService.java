package com.art.jeanyvesart.security.service;

import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.MyCustomer;
import com.art.jeanyvesart.security.user.SecurityUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
//@Qualifier("jdbcUserDetailsService")
public class JdbcUserDetailsService implements UserDetailsService {
    private final Consumer<MyCustomer> consumer;
    private final List<SecurityUserDetails> users = new ArrayList<>();

    public JdbcUserDetailsService(Consumer<MyCustomer> consumer) {
        this.consumer = consumer;
    }


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        SecurityUserDetails userDetails;
        Optional<MyCustomer> optionalMyCustomer = consumer.getResourceById("/customer/account/{email}", Objects.requireNonNull(email),MyCustomer.class);
        System.out.println(email);
        if (optionalMyCustomer.isPresent()) {
            log.info("user info,{}",optionalMyCustomer.get());

            userDetails = new SecurityUserDetails(optionalMyCustomer.get());
        } else {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");

        }
        System.out.println(userDetails);
        return userDetails;
    }
//    return users.stream()
//      .filter(
//         u -> u.getUsername().equals(username)
//      )
//      .findFirst()
//      .orElseThrow(
//        () -> new UsernameNotFoundException("User not found")
//      );
//   }
}