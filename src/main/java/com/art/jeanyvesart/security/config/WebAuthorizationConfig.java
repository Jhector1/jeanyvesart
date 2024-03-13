package com.art.jeanyvesart.security.config;

import com.art.jeanyvesart.controller.ProfileController;
import com.art.jeanyvesart.security.component.authenticationProvider.CustomAuthenticationProvider;
import com.art.jeanyvesart.security.service.CustomOAuth2UserService;
import com.art.jeanyvesart.security.service.CustomOicdUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAuthorizationConfig {

    private final CustomAuthenticationProvider authenticationProvider;
    private final CustomOicdUserService oicdUserService;
    private final CustomOAuth2UserService oAuth2UserService;
    public WebAuthorizationConfig(

            CustomAuthenticationProvider authenticationProvider, CustomOicdUserService oicdUserService, CustomOAuth2UserService oAuth2UserService) {

        this.authenticationProvider = authenticationProvider;
        this.oicdUserService = oicdUserService;
        this.oAuth2UserService = oAuth2UserService;
    }


    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                c -> {

                    c.requestMatchers("/account/**")
                            .authenticated();
                    c.anyRequest()
                            .permitAll();

                });


        http.formLogin(c ->
                c.loginPage("/login")
                        .defaultSuccessUrl("/account/" + ProfileController.getSessionId() + "/profile", true)
                        .permitAll()
        ).oauth2Login(o ->
                o.loginPage("/login")
                        .userInfoEndpoint(u -> u
                                .oidcUserService(oicdUserService)
                                .userService(oAuth2UserService))

                        .defaultSuccessUrl("/account/" + ProfileController.getSessionId() + "/profile", true)
                        .permitAll()
        ).logout(l -> l
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll());

        http.authenticationProvider(authenticationProvider);
       http.csrf(AbstractHttpConfigurer::disable);//.headers(h->h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

}