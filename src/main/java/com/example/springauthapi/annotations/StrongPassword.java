package com.example.springauthapi.annotations;

import com.example.springauthapi.validators.StrongPasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "password is not strong enough";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
