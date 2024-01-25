package com.art.jeanyvesart.dto;

import com.art.jeanyvesart.validation.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor



public class CustomerDto implements MyCurrentUser {
    private String fullName;
    private String id;
    @PasswordMatches(message = "Password Invalid. Password" +
            " must be at least 8 characters and include a lowercase " +
            "letter, uppercase letter, and digit.")
    private String password;
    private String matchingPassword;
   // @ValidEmail(message ="Email is Invalid")
    private String email;
}
