package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.RegisteredUserLoginDataDto;
import com.sockib.doctorofficeapp.services.RegisteredUserDetailsService;
import com.sockib.doctorofficeapp.services.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/token")
@AllArgsConstructor
public class TokenController {

    private TokenService jwtService;
    private RegisteredUserDetailsService registeredUserDetailsService;

    @PostMapping
    public String login(@Valid @RequestBody RegisteredUserLoginDataDto registeredUserLoginDataDto) {
        var username = registeredUserLoginDataDto.getUsername();
        var registerUser = registeredUserDetailsService.loadUserByUsername(username);

        return jwtService.issueToken(registerUser);
    }

}
