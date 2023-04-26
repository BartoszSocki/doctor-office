package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.DoctorInfoDto;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/info")
    public ResponseEntity<DoctorInfoDto> doctor(Principal principal) {
        var username = principal.getName();
        var doctorInfo = doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        var doctorInfoDto = modelMapper.map(doctorInfo, DoctorInfoDto.class);

        return ResponseEntity.ok(doctorInfoDto);
    }
}
