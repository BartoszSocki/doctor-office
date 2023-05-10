package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.*;
import com.sockib.doctorofficeapp.services.DoctorInfoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping(path = "/api/doctor")
public class DoctorInfoController {

    private ModelMapper modelMapper;
    private DoctorInfoService doctorInfoService;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/info")
    public ResponseEntity<DoctorPrivateInfoDto> privateDoctorInfo(Principal principal) {
        var doctorInfo = doctorInfoService.getDoctorInfo(principal.getName());
        var doctorInfoDto = modelMapper.map(doctorInfo, DoctorPrivateInfoDto.class)
                .add(linkTo(methodOn(DoctorInfoController.class).privateDoctorInfo(principal)).withSelfRel())
                .add(linkTo(methodOn(DoctorInfoController.class).publicDoctorInfo(doctorInfo.getId())).withRel("publicDoctorInfo"));

        var userPrivateDataDto = modelMapper.map(doctorInfo.getRegisteredUser(), UserPrivateInfoDto.class);
        doctorInfoDto.setUserPrivateInfoDto(userPrivateDataDto);

        return ResponseEntity.ok(doctorInfoDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/clients")
    public ResponseEntity<List<UserPublicInfoDto>> getDoctorClients() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var clients = doctorInfoService.getDoctorClients(authentication.getName());

        var clientsDto = clients.stream()
                .map(c -> modelMapper.map(c, UserPublicInfoDto.class))
                .toList();

        return ResponseEntity.ok(clientsDto);
    }

    @GetMapping(path = "/{doctorId}/info")
    public ResponseEntity<DoctorPublicInfoDto> publicDoctorInfo(@PathVariable Long doctorId) {
        var doctorInfo = doctorInfoService.getDoctorInfo(doctorId);
        var doctorInfoDto = modelMapper.map(doctorInfo, DoctorPublicInfoDto.class)
                .add(linkTo(methodOn(DoctorInfoController.class).publicDoctorInfo(doctorId)).withSelfRel());

        var userPublicDataDto = modelMapper.map(doctorInfo.getRegisteredUser(), UserPublicInfoDto.class);
        doctorInfoDto.setUserPublicInfoDto(userPublicDataDto);

        return ResponseEntity.ok(doctorInfoDto);
    }
}
