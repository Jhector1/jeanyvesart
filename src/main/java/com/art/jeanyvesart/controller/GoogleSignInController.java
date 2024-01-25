package com.art.jeanyvesart.controller;//package com.art.jeanyvesart.controller;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Arrays;
//
//@RestController
//public class GoogleSignInController {
//
//    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//
//    @PostMapping("/api/google-signin")
//    public String handleGoogleSignIn(@RequestBody String code) {
//        System.out.println(code);
//        try {
//            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
//                    .setAudience(Arrays.asList("YOUR_CLIENT_ID"))
//                    .build();
//
//            GoogleIdToken token = verifier.verify(code);
//
//            if (token != null) {
//                // Valid token, you can extract user information from the token
//                // For example, String email = token.getPayload().getEmail();
//                System.out.println("yeah");
//                return "Success";
//            } else {
//                // Invalid token
//                return "Invalid token";
//            }
//        } catch (Exception e) {
//            // Handle exception
//            return "Error";
//        }
//    }
//}
