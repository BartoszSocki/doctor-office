package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.DoctorRegistrationDataDto;
import com.sockib.doctorofficeapp.services.DoctorRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/doctor/register")
public class DoctorRegistrationController {

//    private DoctorRegistrationService doctorRegistrationService;
//
//    @PostMapping
//    public void register(@Valid @RequestBody DoctorRegistrationDataDto doctorRegistrationDataDto) {
//        doctorRegistrationService.registerDoctor(doctorRegistrationDataDto);
//    }

}
