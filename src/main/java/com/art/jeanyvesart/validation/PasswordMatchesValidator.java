package com.art.jeanyvesart.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, String> {

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        return isValidPassword(password);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }
}
