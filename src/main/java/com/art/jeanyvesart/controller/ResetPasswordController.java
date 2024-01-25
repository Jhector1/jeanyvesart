package com.art.jeanyvesart.controller;

import com.art.jeanyvesart.dto.CustomerDto;
import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.CustomerFavorite;
import com.art.jeanyvesart.model.MyCustomer;
import com.art.jeanyvesart.validation.PasswordMatches;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Controller

@RequestMapping(path = "/reset-password", produces = "application/json")
@Slf4j
public class ResetPasswordController {
    private final PasswordEncoder passwordEncoder;
    private final Consumer<String> consumer;

    public ResetPasswordController(PasswordEncoder passwordEncoder, Consumer<String> consumer) {
        this.passwordEncoder = passwordEncoder;
        this.consumer = consumer;
    }

    @GetMapping
    public String getResetPasswordPage(@RequestParam String token, Model model) {
        log.info("tokeno, {}", token);
        model.addAttribute("token", token);
        return "reset_password";
    }

    @ModelAttribute
    public CustomerDto customerDto() {
        return new CustomerDto();
    }

    @PostMapping
    public String resetPassword(@RequestParam @NonNull String token,
                                @RequestParam("password") @PasswordMatches String newPassword,
                                @RequestParam String confirm_new_password,
                                Model model,
                                @Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                BindingResult bindingResult) {
        ResponseEntity<String> response = null;
        // Validate the tokenlog.
        try {
            log.info("token: ,{}", token);

            if (bindingResult.hasErrors()) {
                //model.addAttribute("customerDto", new CustomerDto());

                return "reset_password";
            } else if (!newPassword.equals(confirm_new_password)) {
                model.addAttribute("error", "Password does not match");
                model.addAttribute("validated", "was-validated");

                return "reset_password";
            }
            MyCustomer customer = new MyCustomer();
            // Set new password
            customer.setPassword(passwordEncoder.encode(newPassword));


            // Save the user
            response = consumer.updateResourceWithPatch(passwordEncoder.encode(newPassword), "/customer/update/token/" + token);


            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());

            return "reset_password";
        }


    }

}
