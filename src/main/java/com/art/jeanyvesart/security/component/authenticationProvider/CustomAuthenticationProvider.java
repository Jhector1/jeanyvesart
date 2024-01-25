package com.art.jeanyvesart.security.component.authenticationProvider;

import com.art.jeanyvesart.security.service.JdbcUserDetailsService;
import com.art.jeanyvesart.security.user.CustomAuthentication;
import com.art.jeanyvesart.security.user.SecurityUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CustomAuthenticationProvider 
implements AuthenticationProvider {
    private final JdbcUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    @Autowired
   private  HttpServletRequest request;

    public CustomAuthenticationProvider(JdbcUserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(
            Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = String.valueOf(
                authentication.getCredentials());
        SecurityUserDetails u = (SecurityUserDetails) userDetailsService.loadUserByUsername(username);



        if (u.getUsername().equals(username) &&
                encoder.matches(password,u.getPassword())) {
            log.info("response, {}", username);

            // Retrieve the session ID
            //String sessionId = session.getId();

            // Optionally, you can also print or log the username and session ID
            ///System.out.println("Session ID: " + sessionId);
            return new CustomAuthentication(
                    username,
                    password,
                    List.of());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }

    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken
                .class
                .isAssignableFrom(authenticationType);
    }
        }