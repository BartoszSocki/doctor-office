package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.ClientPrivateInfoDto;
import com.sockib.doctorofficeapp.model.dto.ClientPublicInfoDto;
import com.sockib.doctorofficeapp.model.dto.UserPrivateInfoDto;
import com.sockib.doctorofficeapp.model.dto.UserPublicInfoDto;
import com.sockib.doctorofficeapp.services.ClientInfoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/client")
@AllArgsConstructor
public class ClientInfoController {

    private ClientInfoService clientInfoService;
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/info")
    public ResponseEntity<ClientPrivateInfoDto> privateClientInfo(Principal principal) {
        var clientInfo = clientInfoService.getClientInfo(principal.getName());
        var clientInfoDto = modelMapper.map(clientInfo, ClientPrivateInfoDto.class)
                .add(linkTo(methodOn(ClientInfoController.class).privateClientInfo(principal)).withSelfRel())
                .add(linkTo(methodOn(ClientInfoController.class).publicClientInfo(clientInfo.getId())).withRel("publicClientInfo"));

        var userPrivateDataDto = modelMapper.map(clientInfo.getRegisteredUser(), UserPrivateInfoDto.class);
        clientInfoDto.setUserPrivateInfoDto(userPrivateDataDto);

        return ResponseEntity.ok(clientInfoDto);
    }

    @GetMapping(path = "/{clientId}/info")
    public ResponseEntity<ClientPublicInfoDto> publicClientInfo(@PathVariable Long clientId) {
        var clientInfo = clientInfoService.getClientInfo(clientId);
        var clientInfoDto = modelMapper.map(clientInfo, ClientPublicInfoDto.class)
                .add(linkTo(methodOn(ClientInfoController.class).publicClientInfo(clientId)).withSelfRel());

        var userPublicDataDto = modelMapper.map(clientInfo.getRegisteredUser(), UserPublicInfoDto.class);
        clientInfoDto.setUserPublicInfoDto(userPublicDataDto);

        return ResponseEntity.ok(clientInfoDto);
    }

}
