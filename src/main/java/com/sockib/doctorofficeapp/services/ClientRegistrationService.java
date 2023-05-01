package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.RegisteredUser;
import com.sockib.doctorofficeapp.exceptions.UserAlreadyRegisteredException;
import com.sockib.doctorofficeapp.model.dto.ClientRegistrationDataDto;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//@AllArgsConstructor
public class ClientRegistrationService {

//    private RegisteredUserRepository clientCredentialsRepository;
//    private ClientInfoRepository clientInfoRepository;
//    private PasswordEncoder passwordEncoder;
//
//    @Transactional
//    public void registerClient(ClientRegistrationDataDto clientRegistrationDataDto) {
//        var username = clientRegistrationDataDto.getUsername();
//        var client = clientCredentialsRepository.findRegisteredUserByUsername(username);
//        client.ifPresent(u -> { throw new UserAlreadyRegisteredException(username); });
//
//        var encodedPassword = passwordEncoder.encode(clientRegistrationDataDto.getPassword());
//
//        var registeredClient = new RegisteredUser();
//        registeredClient.setUsername(username);
//        registeredClient.setPassword(encodedPassword);
//
//        clientCredentialsRepository.save(registeredClient);
//    }

}
