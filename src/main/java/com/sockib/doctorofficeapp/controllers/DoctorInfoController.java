package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import com.sockib.doctorofficeapp.entities.DoctorInfo;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/doctor")
@AllArgsConstructor
public class DoctorInfoController {

    private DoctorInfoRepository doctorInfoRepository;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/info")
    public ResponseEntity<DoctorInfo> doctor(Principal principal) {
        var username = principal.getName();
        var doctorInfo = doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return ResponseEntity.ok(doctorInfo);
    }
}
