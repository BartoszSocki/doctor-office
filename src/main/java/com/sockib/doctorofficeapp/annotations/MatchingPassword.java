package com.sockib.doctorofficeapp.annotations;

import com.sockib.doctorofficeapp.validators.MatchingPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MatchingPasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchingPassword {
    String message() default "passwords are not the same";
    String originalPasswordField();
    String matchingPasswordField();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
