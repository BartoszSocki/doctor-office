package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.RegisteredUserLoginDataDto;
import com.sockib.doctorofficeapp.model.dto.TokenDto;
import com.sockib.doctorofficeapp.services.RegisteredUserDetailsService;
import com.sockib.doctorofficeapp.services.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping(path = "/token")
public class TokenController {

    private TokenService jwtService;
    private RegisteredUserDetailsService registeredUserDetailsService;

    @PostMapping
    public ResponseEntity<TokenDto> login(@Valid @RequestBody RegisteredUserLoginDataDto registeredUserLoginDataDto) {
        var username = registeredUserLoginDataDto.getUsername();
        var registerUser = registeredUserDetailsService.loadUserByUsername(username);

        var token = jwtService.issueToken(registerUser);
        var tokenDto = new TokenDto();
        tokenDto.setToken(token);

        return ResponseEntity.ok(tokenDto);
    }

}
