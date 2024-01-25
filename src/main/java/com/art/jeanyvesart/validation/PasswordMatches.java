package com.art.jeanyvesart.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD,ANNOTATION_TYPE, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class) // Points to the validator class
@Documented
public @interface PasswordMatches {

    // Default error message if validation fails
    String message() default "Passwords don't match";

    // Groups to which this constraint belongs (default is an empty array)
    Class<?>[] groups() default {};

    // Payload associated with this constraint (default is an empty array)
    Class<? extends Payload>[] payload() default {};
}
