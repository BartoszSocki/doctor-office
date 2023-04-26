package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.ClientPrivateInfoDto;
import com.sockib.doctorofficeapp.model.dto.ClientPublicInfoDto;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import com.sockib.doctorofficeapp.services.ClientInfoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/client/info")
@AllArgsConstructor
public class ClientInfoController {

    private ClientInfoService clientInfoService;
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/private")
    public ResponseEntity<ClientPrivateInfoDto> privateClientInfo(Principal principal) {
        var clientInfo = clientInfoService.getClientInfo(principal.getName());
        var clientInfoDto = modelMapper.map(clientInfo, ClientPrivateInfoDto.class);

        clientInfoDto.add(linkTo(methodOn(ClientInfoController.class).privateClientInfo(principal)).withSelfRel());
        clientInfoDto.add(linkTo(methodOn(ClientInfoController.class).publicClientInfo(principal)).withRel("publicClientInfo"));

        return ResponseEntity.ok(clientInfoDto);
    }

    @GetMapping(path = "/public")
    public ResponseEntity<ClientPublicInfoDto> publicClientInfo(Principal principal) {
        var clientInfo = clientInfoService.getClientInfo(principal.getName());
        var clientInfoDto = modelMapper.map(clientInfo, ClientPublicInfoDto.class);

        clientInfoDto.add(linkTo(methodOn(ClientInfoController.class).publicClientInfo(principal)).withSelfRel());
        clientInfoDto.add(linkTo(methodOn(ClientInfoController.class).privateClientInfo(principal)).withRel("privateClientInfo"));

        return ResponseEntity.ok(clientInfoDto);
    }

}
