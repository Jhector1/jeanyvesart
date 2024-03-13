package com.art.jeanyvesart;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
      //  registry.addViewController("/cart/checkout/success").setViewName("success_page");
        registry.addViewController("/cart/checkout/cancel").setViewName("error_page");
        registry.addViewController("/terms-of-service").setViewName("terms_and_conditions");
       // registry.addViewController("/login/account").setViewName("userProfile");


        registry.addViewController("/exhibitions").setViewName("exhibitions_page");
        registry.addViewController("/about").setViewName("about");
      // registry.addViewController("/contact").setViewName("contact");
    }
}
