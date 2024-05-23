package com.art.jeanyvesart.helper.api;

import com.art.jeanyvesart.helper.Helper;
import com.art.jeanyvesart.model.Inventory;
import com.art.jeanyvesart.model.MyOrder;
import com.art.jeanyvesart.service.csrf.Token;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class Consumer<E> {
    @Value("${baseUrl}")
    private String sever_domain;
    private  Helper helper;

    private static String baseUrl;

    @PostConstruct
    public void init() {
        baseUrl = sever_domain;
    }

    private  HttpServletRequest request;
    static HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
    private static final RestTemplate restTemplate = new RestTemplate(factory);

    public Consumer(Helper helper, HttpServletRequest request) {
        this.helper = helper;
        this.request = request;
    }


    public Optional<E> getResourceById(String url, Object id, Class<E> objClass) {
        return Optional.ofNullable(restTemplate.getForObject(baseUrl + url,
                objClass, id));
    }


    public Optional<E> createResource(String url, E obj) {

        // Step 3: Prepare the request body (if needed)

        // Step 4: Prepare the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String identifier = helper.getCookieValue("user12345");

        headers.add("X-IDENTIFIER", identifier.substring(identifier.length() / 2));
        headers.add("X-CSRF-TOKEN", getCSRF().getToken());
        // Step 5: Combine headers and request body
        HttpEntity<E> requestEntity = new HttpEntity<>(obj, headers);

        return Optional.ofNullable(restTemplate.postForObject(baseUrl + url,
                requestEntity, (Class<E>) Object.class));
    }

    public ResponseEntity<E> updateResourceWithPatch(E obj, String url) {
        String identifier = helper.getCookieValue("user12345");
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-IDENTIFIER", identifier.substring(identifier.length() / 2));
        headers.add("X-CSRF-TOKEN", getCSRF().getToken());

        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<E> requestEntity = new HttpEntity<>(obj, headers);

        ResponseEntity<E> response = restTemplate.exchange(
                baseUrl + url,
                HttpMethod.PATCH,
                requestEntity,
                (Class<E>) Object.class
        );

        // Handle the response as needed
        log.info("Response status: {} ", response.getStatusCode());
        return response;

    }

    public  Token getCSRF() {
        String url = baseUrl + "/csrf/token";
        log.info("baseurl, {}", baseUrl);
        String identifier = helper.getCookieValue( "user12345");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-IDENTIFIER", identifier.substring(identifier.length() / 2));

        // Create HttpEntity with headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Send GET request with headers
        ResponseEntity<Token> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Token.class);
        log.info("token obj: {}", Objects.requireNonNull(response.getBody()).getToken());
        // Process response as needed
        return response.getBody();
    }

}
