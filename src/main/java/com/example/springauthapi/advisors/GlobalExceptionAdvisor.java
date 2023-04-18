package com.example.springauthapi.advisors;

import com.example.springauthapi.exceptions.UserAlreadyRegisteredException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvisor {

    @ExceptionHandler(value = UserAlreadyRegisteredException.class)
    public ResponseEntity<?> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getBody());
    }

}
