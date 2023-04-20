package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.services.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/token")
@AllArgsConstructor
public class TokenController {

    private TokenService jwtService;

    @PostMapping
    public String authentication(Authentication authentication) {
        return jwtService.issueTokenFor(authentication);
    }

//    @PostMapping("/register")
//    public void register(@Valid @RequestBody UserCredentialsDto userData) {
//        userRegistrationService.registerUser(userData);
//    }
}
