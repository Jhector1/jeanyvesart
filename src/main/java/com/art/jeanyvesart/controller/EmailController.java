//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.dto.EmailClient;
//import com.art.jeanyvesart.helper.Helper;
//import com.art.jeanyvesart.helper.api.Consumer;
//import com.art.jeanyvesart.model.MyCustomer;
//
//import com.art.jeanyvesart.security.service.EmailServiceImpl;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping(produces = "application/json")
//
//public class EmailController {
//    final
//    EmailServiceImpl emailService;
//    private final Consumer<MyCustomer> consumer;
//
//    public EmailController(EmailServiceImpl emailService, Consumer<MyCustomer> consumer) {
//        this.emailService = emailService;
//        this.consumer = consumer;
//    }
//
//    @PostMapping("/sendemail")
//    public ResponseEntity<String> sendEmail(@RequestBody EmailClient emailClient) {
//        try {
//            emailService.sendSimpleMessage(emailClient.getEmailFrom(), emailClient.getEmailTo(), "Thanks For Contacting Jean Yves Art", "Thank you for reaching out to Jean Yves Art. We will respond to your inquiry as soon as possible.");
//
//
//            emailService.sendMessageWithAttachment(emailClient.getEmailTo(), emailClient.getEmailFrom(), emailClient.getSubject(), emailClient.getMessage(), emailClient.getFileAttachment());
//            return new ResponseEntity<>("Thank for your message", HttpStatus.OK);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    @PostMapping("/reset-password-email")
//    public ResponseEntity<String> send_reset_email_password(@RequestBody EmailClient emailClient, HttpServletResponse response) {
//        try {
////System.out.println(emailClient.getEmailTo());
//            Optional<MyCustomer> optionalMyCustomer = consumer.getResourceById("/customer/account/{email}", Objects.requireNonNull(Helper.getAuthenticatedEmail()),MyCustomer.class);
//           // if (optionalMyCustomer.isPresent()) {
//                String uuidString = UUID.randomUUID().toString();
//                MyCustomer customer = optionalMyCustomer.get();
//                //emailService.sendSimpleMessage(emailClient.getEmailFrom(), emailClient.getEmailTo(), "Thanks For Contacting Jean Yves Art", "Thank you for reaching out to Jean Yves Art. We will respond to your inquiry as soon as possible.");
//                String url = "http://localhost:8080/reset-password?token=" + uuidString +"&action=reset_password";
//                String body = "Etsy\n" +
//                        "Hi "+customer.getFullName()+", let's reset your password.\n" +
//                        url+" Your Password" +
//                        "\n" +
//                        "If the above button does not work for you, copy and paste the following into your browser's address bar:";
//                emailService.sendSimpleMessage(emailClient.getEmailTo(), "myart@jeanyveshector.com", "Reset Your Password", body);
//                customer.setResetToken(uuidString);
//                customer.setResetTokenDate(new Date());
//                customer.setResetTokenUsed(false);
//            consumer.createResource("/customer/account/save",customer);
//                // Create a new cookie
//            Cookie cookie = new Cookie("token-validation", uuidString);
//
//            // Set cookie properties (optional)
//            //cookie.setMaxAge(24 * 60 * 60); // Set the cookie's maximum age in seconds (1 day in this example)
//            cookie.setPath("/"); // Set the path for which the cookie is valid (root path in this example)
//
//            // Add the cookie to the response
//            response.addCookie(cookie);
//            String resetPasswordMessage = "To reset your password, please use the sign-in link sent to " + emailClient.getEmailTo() + ".\n" +
//                    "\n" +"It will expired after 30minutes."+
//
//                    "Ensure to double-check your email; it must be associated with an account to receive this\n" +
//                    "message containing a link to reset your password.";
//
//            return new ResponseEntity<>(resetPasswordMessage, HttpStatus.OK);
//           // }
//
//
//
//          //  return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//}