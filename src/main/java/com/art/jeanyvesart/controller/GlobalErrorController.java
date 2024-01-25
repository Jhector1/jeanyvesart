package com.art.jeanyvesart.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Get the HTTP status code
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        // Set the status code in the model for the error page to access
        model.addAttribute("statusCode", statusCode);

        return "404"; // This maps to the 404.html template
    }

    public String getErrorPath() {
        return "/error";
    }
}
