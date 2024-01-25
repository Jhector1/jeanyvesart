package com.art.jeanyvesart.helper.api;

import com.art.jeanyvesart.model.Inventory;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Service
@Slf4j
public class Consumer<E> {
    @Value("${baseUrl}")
    private  String baseUrl;
    static HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
    private static final RestTemplate restTemplate = new RestTemplate(factory);
    public  Optional<Inventory > getResourceById2(String url, Object id) {
        return Optional.ofNullable(restTemplate.getForObject(baseUrl+ url,
                Inventory.class, id));
    }
    public Optional<E> getResourceById(String url, Object id, Class<E> objClass) {
        return Optional.ofNullable(restTemplate.getForObject(baseUrl+url,
                objClass, id));
    }
    public Optional<E> createResource(String url,E obj) {
        return Optional.ofNullable(restTemplate.postForObject(baseUrl+url,
                obj, (Class<E>) Object.class));
    }
    public  ResponseEntity<E> updateResourceWithPatch(E obj, String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<E> requestEntity = new HttpEntity<>(obj, headers);

        ResponseEntity<E> response = restTemplate.exchange(
                baseUrl+url,
                HttpMethod.PATCH,
                requestEntity,
                (Class<E>) Object.class
        );

        // Handle the response as needed
        log.info("Response status: {} " , response.getStatusCode());
        return response;

    }


}
