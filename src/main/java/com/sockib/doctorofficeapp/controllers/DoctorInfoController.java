package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.DoctorPrivateInfo;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/doctor")
@AllArgsConstructor
public class DoctorInfoController {

    private DoctorInfoRepository doctorInfoRepository;
    private RegisteredUserRepository registeredUserRepository;

    @GetMapping(path = "/account")
    public ResponseEntity<DoctorPrivateInfo> getDoctorInfo(Principal principal) {
        var name = principal.getName();
        var user = registeredUserRepository.findUserAuthByUsername(name)
                .orElseThrow(() -> new RuntimeException("TODO"));
        var doctorInfo = doctorInfoRepository.findDoctorInfoByRegisteredUser(user)
                .orElseThrow(() -> new RuntimeException("TODO"));
        return ResponseEntity.ok(doctorInfo);
    }

}
