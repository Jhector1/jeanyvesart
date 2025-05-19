package com.art.jeanyvesart.helper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class Helper {

    public final HttpServletRequest request;
    public final HttpServletResponse response;

    public Helper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
//    private final CustomerRepository customerRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public Helper(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
//        this.customerRepository = customerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public String getCookieValue(String cookieName) {
        Cookie[] cookies = request.getCookies();
        String cookieVal = null;

        if (cookies != null) {
            // Iterate through the cookies
            for (Cookie cookie : cookies) {

                if (cookie.getName().trim().equals(cookieName)) {
                    // Found the desired cookie
                    return cookie.getValue();
                }

            }
        }
        cookieVal = UUID.randomUUID().toString();
        setCookieValue(cookieName, cookieVal);
        return cookieVal;
    }

    public String getAuthenticatedEmail() {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication a = context.getAuthentication();
        String email;
        if (a.getPrincipal() instanceof OAuth2User oAuth2User) {
            email = checkEmail(oAuth2User);
        } else {
            email = a.getPrincipal().toString();
        }
        return email;


    }

    public String checkEmail(OAuth2User oAuth2User) {
        log.info("user {}", oAuth2User.toString());
        String email = oAuth2User.getAttribute("email");


        if (email == null) {
            String id = oAuth2User.getAttribute("id");
            String name = oAuth2User.getAttribute("name");
            if(name==null){
                name="anonymous";
            }

            return name.replace(" ", "") + id + "@jeanyveshector.com";
        } else {
            return email;
        }
    }

    //
//
    public String getSessionId() {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication a = context.getAuthentication();
        String sessionId;
        if (a == null) {
            sessionId = UUID.randomUUID().toString();
        } else {
            WebAuthenticationDetails details = (WebAuthenticationDetails) a.getDetails();

            // Access the session ID
            sessionId = details.getSessionId();


        }
        return sessionId;
    }

    public void setCookieValue(String cookiename, String cookievalue) {
        // Create a cookie
        Cookie cookie = new Cookie(cookiename, cookievalue);

        // Optional: Set additional attributes for the cookie
        // cookie.setMaxAge(3600); // Set the cookie's maximum age in seconds (1 hour in this example)
        cookie.setPath("/"); // Set the path for which the cookie is valid (root path in this example)

        // Add the cookie to the response
        response.addCookie(cookie);


        // Other response logic, if needed
    }

}
