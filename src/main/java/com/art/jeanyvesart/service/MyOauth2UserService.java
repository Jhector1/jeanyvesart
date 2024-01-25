package com.art.jeanyvesart.service;

import com.art.jeanyvesart.helper.Helper;
import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.MyCustomer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
@Slf4j
public class MyOauth2UserService {
    private final PasswordEncoder passwordEncoder;
    private final Consumer<MyCustomer> consumer;


    public MyOauth2UserService(PasswordEncoder passwordEncoder, Consumer<MyCustomer> consumer) {
        this.passwordEncoder = passwordEncoder;
        this.consumer = consumer;
    }



    public void processOAuth2User(OAuth2User oAuth2User) {


            MyCustomer newUser = createUserFromOAuth2User(oAuth2User);
            consumer.createResource("/customer/account/save",newUser);

    }

    private MyCustomer createUserFromOAuth2User(OAuth2User oAuth2User) {
        // Customize this method to create a User entity from OAuth2 user information
        MyCustomer newUser = new MyCustomer();
        newUser.setEmail(Helper.checkEmail(oAuth2User));
        newUser.setPassword(passwordEncoder.encode("NewPassword2@"));
        newUser.setFullName(oAuth2User.getAttribute("name"));
        // Set other user details as needed
        return newUser;
    }
}
