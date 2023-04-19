package com.example.springauthapi.validators;

import com.example.springauthapi.annotations.MatchingPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class MatchingPasswordValidator implements ConstraintValidator<MatchingPassword, Object> {

    private String matchingPasswordField;
    private String originalPasswordField;

    public void initialize(MatchingPassword matchingPassword) {
        this.matchingPasswordField = matchingPassword.matchingPasswordField();
        this.originalPasswordField = matchingPassword.originalPasswordField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object originalPasswordValue = new BeanWrapperImpl(o).getPropertyValue(originalPasswordField);
        Object matchingPasswordValue = new BeanWrapperImpl(o).getPropertyValue(matchingPasswordField);

        return originalPasswordValue != null && originalPasswordValue.equals(matchingPasswordValue);
    }
}
