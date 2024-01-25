//package com.art.jeanyvesart.security.service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import java.util.Base64;
//import java.util.Properties;
//
//@Service
//public class EmailServiceImpl {
//    private final JavaMailSenderImpl emailSender;
//    public EmailServiceImpl(JavaMailSenderImpl emailSender) {
//        this.emailSender = emailSender;
//    }
//
//    public void sendSimpleMessage(String to,
//            String from, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        Properties properties = new Properties();
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//
//        emailSender.setJavaMailProperties(properties);
//        emailSender.send(message);
//        System.out.println("...send");
//    }
//
//    public void sendMessageWithAttachment(
//            String to, String from, String subject, String text, String pathToAttachment) {
//        // ...
//
//        try {
//            MimeMessage message = emailSender.createMimeMessage();
//
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//
//            helper.setFrom(from);
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text);
//            byte[] fileBytes = pathToAttachment.getBytes();
//
//            // Convert the byte array to Base64 encoding
////            byte[] fileBytes = Base64.getDecoder().decode(pathToAttachment);
//            String base64File = Base64.getEncoder().encodeToString(fileBytes);
//
//
//
//            helper.addAttachment("invoice"+pathToAttachment, new ByteArrayResource(Base64.getDecoder().decode(base64File)));
//            Properties properties = new Properties();
//            properties.setProperty("mail.smtp.auth", "true");
//            properties.setProperty("mail.smtp.starttls.enable", "true");
//
//            emailSender.setJavaMailProperties(properties);
//            emailSender.send(message);
//
//            // ...
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
//
