package com.example.springauthapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyRegisteredException extends ResponseStatusException {
    public UserAlreadyRegisteredException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
