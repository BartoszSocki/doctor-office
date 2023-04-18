package com.example.springauthapi.controllers;

import com.example.springauthapi.model.dto.UserRegisterDataDto;
import com.example.springauthapi.services.JwtService;
import com.example.springauthapi.services.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@AllArgsConstructor
public class UserAuthActionController {

    private UserRegistrationService userRegistrationService;
    private JwtService jwtService;

    @GetMapping(path = "/authentication")
    public String authentication(Authentication authentication) {
        return jwtService.issueTokenFor(authentication);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRegisterDataDto userData) {
        userRegistrationService.registerUser(userData);
    }
}
