package com.example.springauthapi.validators;

import ch.qos.logback.core.boolex.Matcher;
import com.example.springauthapi.annotations.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    private static final String STRONG_PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&–?*$^+=<>]).{8,}$";
    private static final Pattern pattern = Pattern.compile(STRONG_PASSWORD_PATTERN);

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        var matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
