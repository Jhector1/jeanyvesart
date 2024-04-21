package com.art.jeanyvesart.controller;

import com.art.jeanyvesart.helper.api.Consumer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;


@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @Value("${baseUrl}")
    private String baseUrl;
    private Consumer consumer;
    RestTemplate rest = new RestTemplate();
    @ModelAttribute
    public void addGlobalAttributes(HttpServletRequest request,Model model) {
        // Add global configuration data to the model
        model.addAttribute("apiBaseUrl", baseUrl);
        model.addAttribute("token",     consumer.getCSRF().getToken());

    }



    public GlobalControllerAdvice( Consumer consumer) {

        this.consumer = consumer;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
