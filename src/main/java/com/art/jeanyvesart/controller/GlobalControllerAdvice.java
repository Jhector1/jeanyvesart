package com.art.jeanyvesart.controller;

import com.art.jeanyvesart.service.csrf.Token;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.art.jeanyvesart.helper.api.Consumer.getCSRF;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {
    private final CsrfToken csrfToken;
    @Value("${baseUrl}")
    private String baseUrl;
    RestTemplate rest = new RestTemplate();
    @ModelAttribute
    public void addGlobalAttributes(HttpServletRequest request,Model model) {
        // Add global configuration data to the model
        model.addAttribute("apiBaseUrl", baseUrl);
        model.addAttribute("token",     getCSRF(request).getToken());

    }



    public GlobalControllerAdvice(CsrfToken csrfToken) {
        this.csrfToken = csrfToken;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
