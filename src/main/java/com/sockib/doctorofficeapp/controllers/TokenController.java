package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.UserLoginDataDto;
import com.sockib.doctorofficeapp.enums.Role;
import com.sockib.doctorofficeapp.services.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping(path = "/token")
@AllArgsConstructor
public class TokenController {

//    private TokenService jwtService;
//
//    @PostMapping
//    public String login(@Valid @RequestBody UserLoginDataDto userLoginDataDto) {
//        if (userLoginDataDto.getRole().equals(Role.CLIENT.value())) {
//            var client = clientUserDetailsService.loadUserByUsername(userLoginDataDto.getUsername());
//            return jwtService.issueToken(client);
//        }
//
//        if (userLoginDataDto.getRole().equals(Role.DOCTOR.value())) {
//            var doctor = doctorUserDetailsService.loadUserByUsername(userLoginDataDto.getUsername());
//            return jwtService.issueToken(doctor);
//        }
//
//        return null;
//    }

}
