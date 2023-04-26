package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.ClientRegistrationDataDto;
import com.sockib.doctorofficeapp.services.ClientRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/client/register")
public class ClientRegistrationController {

    private ClientRegistrationService clientRegistrationService;

    @PostMapping
    public void register(@Valid @RequestBody ClientRegistrationDataDto clientRegistrationDataDto) {
        clientRegistrationService.registerClient(clientRegistrationDataDto);
    }

}
