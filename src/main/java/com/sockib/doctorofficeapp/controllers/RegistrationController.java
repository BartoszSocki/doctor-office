package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.ClientRegistrationDataDto;
import com.sockib.doctorofficeapp.model.dto.DoctorRegistrationDataDto;
import com.sockib.doctorofficeapp.services.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

@RequestMapping(path = "/register")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping(path = "/client")
    public void registerClient(@Valid @RequestBody ClientRegistrationDataDto clientRegistrationDataDto) {
        registrationService.registerClient(clientRegistrationDataDto);
    }

    @PostMapping(path = "/doctor")
    public void registerDoctor(@Valid @RequestBody DoctorRegistrationDataDto doctorRegistrationDataDto) {
        registrationService.registerDoctor(doctorRegistrationDataDto);
    }

}
