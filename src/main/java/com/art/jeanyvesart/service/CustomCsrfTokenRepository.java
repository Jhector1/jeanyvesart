//package com.art.jeanyvesart.service;
//
//;
//
//import com.art.jeanyvesart.helper.api.Consumer;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.DefaultCsrfToken;
//import org.springframework.security.web.csrf.DeferredCsrfToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Optional;
//import java.util.UUID;
//@Slf4j
//@Component
//public class CustomCsrfTokenRepository implements CsrfTokenRepository {
//    RestTemplate restTemplate = new RestTemplate();
//    private final Consumer<String> consumer;
//
//    public CustomCsrfTokenRepository(Consumer<String> consumer) {
//        this.consumer = consumer;
//
//    }
//
//    // Omitted constructor
//
//
//
//    @Override
//    public void saveToken(
//            CsrfToken csrfToken,
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse) {
//        String identifier =
//                httpServletResponse.getHeader("X-IDENTIFIER");
//        log.info("identifier save, {}",identifier);
//        if(identifier==null){
//            identifier = httpServletRequest.getHeader("X-IDENTIFIER");
//        }
//        Optional<Token> existingToken =
//                jpaTokenRepository.findTokenByIdentifier(identifier);
//
//        if (existingToken.isPresent()) {
//            Token token = existingToken.get();
//            System.out.println(csrfToken.getToken());
//            token.setToken(csrfToken.getToken());
//        } else {
//            Token token = new Token();
//            token.setToken(csrfToken.getToken());
//            token.setIdentifier(identifier);
//            jpaTokenRepository.save(token);
//        }
//    }
//
//    @Override
//
//    public  CsrfToken  generateToken(HttpServletRequest httpServletRequest) {
//     return  null;
//    }
//    @Override
//
//
//    public  org.springframework.security.web.csrf.CsrfToken  loadToken(
//            HttpServletRequest httpServletRequest) {
//
//
//
//        return null;
//    }
//    //  private HttpHeaders getHeadersFromOtherEndpoint() {
////    // Call another endpoint within the same application
////
////    ResponseEntity<String> response = restTemplate.getForEntity("/otherendpoint", String.class);
////    return response.getHeaders();
////  }
//
//}