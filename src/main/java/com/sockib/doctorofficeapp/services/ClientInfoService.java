package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import com.sockib.doctorofficeapp.entities.RegisteredUser;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientInfoService {

    private ClientInfoRepository clientInfoRepository;

    public ClientInfo getClientInfo(String username) {
        return clientInfoRepository.findClientInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public ClientInfo getClientInfo(Long id) {
        return clientInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TODO"));
    }

}
