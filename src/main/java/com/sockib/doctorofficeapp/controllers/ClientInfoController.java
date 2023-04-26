package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import com.sockib.doctorofficeapp.model.dto.ClientInfoDto;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
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
@RequestMapping(path = "/api/client")
@AllArgsConstructor
public class ClientInfoController {

    private ClientInfoRepository clientInfoRepository;
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/info")
    public ResponseEntity<ClientInfoDto> client(Principal principal) {
        var username = principal.getName();
        var clientInfo = clientInfoRepository.findClientInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        var clientInfoDto = modelMapper.map(clientInfo, ClientInfoDto.class);

        return ResponseEntity.ok(clientInfoDto);
    }

}
