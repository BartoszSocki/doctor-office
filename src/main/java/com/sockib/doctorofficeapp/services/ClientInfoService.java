package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor

@Service
public class ClientInfoService {

    private ClientInfoRepository clientInfoRepository;

    public ClientInfo getClientInfo(String username) {
        log.trace("getting client info by username: " + username);

        return clientInfoRepository.findClientInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public ClientInfo getClientInfo(Long id) {
        log.trace("getting client info by id: " + id);

        return clientInfoRepository.findClientInfoById(id)
                .orElseThrow(() -> new RuntimeException("TODO"));
    }

}
