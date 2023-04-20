package com.sockib.doctorofficeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/doctor")
public class DoctorTestController {

    @GetMapping(path = "/test")
    public String doctor() {
        return "doctor";
    }

}
