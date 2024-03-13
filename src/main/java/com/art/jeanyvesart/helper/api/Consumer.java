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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class Consumer<E> {
    @Value("${baseUrl}")
    private String sever_domain;

    private static String baseUrl;

    @PostConstruct
    public void init() {
        baseUrl = sever_domain;
    }

    private final HttpServletRequest request;
    static HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
    private static final RestTemplate restTemplate = new RestTemplate(factory);

    public Consumer(HttpServletRequest request) {
        this.request = request;
    }

    public Optional<Inventory> getResourceById2(String url, Object id) {
        return Optional.ofNullable(restTemplate.getForObject(baseUrl + url,
                Inventory.class, id));
    }

    public Optional<E> getResourceById(String url, Object id, Class<E> objClass) {
        return Optional.ofNullable(restTemplate.getForObject(baseUrl + url,
                objClass, id));
    }

    public Optional<List<MyOrder>> getResourceByIdParametized(String url, Object id, Class<List<MyOrder>> objClass) {
        return Optional.ofNullable((List<MyOrder>) restTemplate.getForObject(baseUrl + url,
                objClass, id));
    }

    public Optional<E> createResource(String url, E obj) {

        // Step 3: Prepare the request body (if needed)

        // Step 4: Prepare the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String identifier = Helper.getCookieValue(request, "user12345");

        headers.add("X-IDENTIFIER", identifier.substring(identifier.length() / 2));
        headers.add("X-CSRF-TOKEN", getCSRF(request).getToken());
        // Step 5: Combine headers and request body
        HttpEntity<E> requestEntity = new HttpEntity<>(obj, headers);

        return Optional.ofNullable(restTemplate.postForObject(baseUrl + url,
                requestEntity, (Class<E>) Object.class));
    }

    public ResponseEntity<E> updateResourceWithPatch(E obj, String url) {
        String identifier = Helper.getCookieValue(request, "user12345");
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-IDENTIFIER", identifier.substring(identifier.length() / 2));
        headers.add("X-CSRF-TOKEN", getCSRF(request).getToken());

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

    public static Token getCSRF(HttpServletRequest request) {
        String url = baseUrl + "/csrf/token";
        log.info("baseurl, {}", baseUrl);
        String identifier = Helper.getCookieValue(request, "user12345");
//log.info("cookie user id : {}", Helper.getCookieValue(request, "user12345"));
        // Create HttpHeaders and add custom headers
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
