package com.art.jeanyvesart.security.service;

import com.art.jeanyvesart.service.MyOauth2UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    private final MyOauth2UserService myOauth2UserService;


    public CustomOAuth2UserService(MyOauth2UserService myOauth2UserService) {

        this.myOauth2UserService = myOauth2UserService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("response, {}", "Google try");

        // Process the user information and create an account if it doesn't exist
        myOauth2UserService.processOAuth2User(oAuth2User);

        return oAuth2User;
    }


}

