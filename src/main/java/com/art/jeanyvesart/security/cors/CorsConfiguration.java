package com.art.jeanyvesart.security.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS requests to all paths
            .allowedOrigins("*") // Allow all origins (not recommended for production)
            .allowedMethods("*") // Allow all HTTP methods (GET, POST, etc.)
            .allowedHeaders("*"); // Allow all headers
    }
}
