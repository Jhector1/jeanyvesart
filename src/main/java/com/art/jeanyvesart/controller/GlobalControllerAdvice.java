package com.art.jeanyvesart.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Value("${baseUrl}")
    private String baseUrl;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        // Add global configuration data to the model
        model.addAttribute("apiBaseUrl", baseUrl);
    }
}
