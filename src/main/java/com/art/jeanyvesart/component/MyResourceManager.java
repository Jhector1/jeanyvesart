//package com.art.jeanyvesart.component;
//
//import com.art.jeanyvesart.dto.MyOrderDto;
//import com.art.jeanyvesart.model.*;
//import com.art.jeanyvesart.repository.CustomerDataHelperRepository;
//import com.art.jeanyvesart.repository.CustomerRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.*;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//@Slf4j
//public class MyResourceManager {
//    private static HttpServletRequest request;
//    private static CustomerRepository customerRepository;
//    static HttpComponentsClientHttpRequestFactory factory =
//            new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
//    private static final RestTemplate restTemplate = new RestTemplate(factory);
//
//    public MyResourceManager(HttpServletRequest request, CustomerRepository customerRepository) {
//        MyResourceManager.request = request;
//        MyResourceManager.customerRepository = customerRepository;
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    public static void updateInventoryWithPut(Inventory inventory, String url) {
//        restTemplate.put(
//                url, inventory, inventory.getId());
//
//    }
//
//    public static void createOrder(MyOrderDto myOrder, String url) {
//        restTemplate.postForObject(url,
//                myOrder, MyOrder.class);
//    }
//
//
//    public static void updateInventoryWithPatch(Inventory inventory, String url) {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Inventory> requestEntity = new HttpEntity<>(inventory, headers);
//
//        ResponseEntity<Inventory> response = restTemplate.exchange(
//                url,
//                HttpMethod.PATCH,
//                requestEntity,
//                Inventory.class
//        );
//
//        // Handle the response as needed
//        log.info("Response status: {} " , response.getStatusCode());
//
//    }
//
//    public static <H extends CustomerDataHelper, R extends CustomerDataHelperRepository<H>> void manageUserData(R repository, String deleteEndPoint, String id) {
//        List<H> customers = (List<H>) repository.findAll();
//        if (customers.stream().anyMatch(customerCartHelper -> customerCartHelper.getMyProduct().getId() == Long.parseLong(id))) {
//            MyResourceManager.deleteCustomerData(deleteEndPoint, id);
//        }
//
//    }
//
//    public static void deleteCustomerData(String url, String id) {
//        restTemplate.delete(url, id);
//    }
//
//    public static <H extends CustomerDataHelper, M extends CustomerData<H>> void removeExpiredUserData(List<H> resource) {
//        long deadline = 60000;//2592000000L;
//
//        String type = "";
//        if (resource instanceof CustomerCart) {
//            type = "cart";
//        } else {
//            type = "favorite";
//        }
//        long currentTime = new Date().getTime();
//        String finalType = type;
//        //System.out.println(finalType);
//        resource.forEach(helper -> {
//            String deleteResourceEndpoint = MyResourceManager.updateEndpoint("/" + finalType + "/artworks/delete-artwork/{id}");
//
//            if (helper.getDate().getTime() + deadline < currentTime) {
//                deleteCustomerData(deleteResourceEndpoint, String.valueOf(helper.getMyProduct().getId()));
//            }
//
//        });
//
//    }
//
//    public static void removeExpiredUser(String userId) {
//        long deadline = 60000;//2592000000L;
//        Optional<MyCustomer> customerOptional = customerRepository.findById(userId);
//        if (customerOptional.isPresent()) {
//            MyCustomer customer = customerOptional.get();
//
//            long currentTime = new Date().getTime();
//
//            if (customer.getExpirationDate().getTime() + deadline < currentTime) {
//                deleteCustomerData(updateEndpoint("/customer/{id}"), userId);
//            }
//
//        }
//    }
//
//    public static String getBaseUrl() {
//        String scheme = request.getScheme();             // "http" or "https"
//        String serverName = request.getServerName();     // "localhost" or IP address
//        int portNumber = request.getServerPort();        // Port number
//        String contextPath = request.getContextPath();   // Application context path
//
//        // Build the base URL
//        StringBuilder baseUrlBuilder = new StringBuilder();
//        baseUrlBuilder.append(scheme).append("://").append(serverName);
//        if (portNumber != 80 && portNumber != 443) {
//            baseUrlBuilder.append(":").append(portNumber);
//        }
//        baseUrlBuilder.append(contextPath);
//        return baseUrlBuilder.toString();
//
//
//    }
//
//    public static String updateEndpoint(String endpoint) {
//        return getBaseUrl() + endpoint;
//
//    }
//
//}
