package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.RegisteredUserLoginDataDto;
import com.sockib.doctorofficeapp.model.dto.TokenDto;
import com.sockib.doctorofficeapp.services.RegisteredUserDetailsService;
import com.sockib.doctorofficeapp.services.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping(path = "/token")
public class TokenController {

    private TokenService jwtService;
    private PasswordEncoder passwordEncoder;
    private RegisteredUserDetailsService registeredUserDetailsService;

    @PostMapping
    public ResponseEntity<TokenDto> login(@Valid @RequestBody RegisteredUserLoginDataDto registeredUserLoginDataDto) {
        var username = registeredUserLoginDataDto.getUsername();
        var password = registeredUserLoginDataDto.getPassword();
        var registeredUser = registeredUserDetailsService.loadUserByUsername(username);

        if (!registeredUser.getPassword().equals(password)) {
            return ResponseEntity.badRequest().build();
        }

        var token = jwtService.issueToken(registeredUser);
        var tokenDto = new TokenDto();
        tokenDto.setToken(token);

        return ResponseEntity.ok(tokenDto);
    }

}
