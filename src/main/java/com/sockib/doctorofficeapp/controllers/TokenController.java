package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.UserLoginDataDto;
import com.sockib.doctorofficeapp.roles.Role;
import com.sockib.doctorofficeapp.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

// very bad temporary solution
// TODO implement custom auth manager

@RestController
@RequestMapping(path = "/token")
public class TokenController {

    private TokenService jwtService;
    private UserDetailsService doctorUserDetailsService;
    private UserDetailsService clientUserDetailsService;

    public TokenController(TokenService jwtService,
                           @Qualifier("doctorDetailsService") UserDetailsService doctorUserDetailsService,
                           @Qualifier("clientDetailsService") UserDetailsService clientUserDetailsService) {
        this.jwtService = jwtService;
        this.doctorUserDetailsService = doctorUserDetailsService;
        this.clientUserDetailsService = clientUserDetailsService;
    }

    @PostMapping
    public String login(@Valid @RequestBody UserLoginDataDto userLoginDataDto) {
        if (userLoginDataDto.getRole().equals(Role.CLIENT.value())) {
            var client = clientUserDetailsService.loadUserByUsername(userLoginDataDto.getUsername());
            return jwtService.issueToken(client);
        }

        if (userLoginDataDto.getRole().equals(Role.DOCTOR.value())) {
            var doctor = doctorUserDetailsService.loadUserByUsername(userLoginDataDto.getUsername());
            return jwtService.issueToken(doctor);
        }

        return null;
    }

}
