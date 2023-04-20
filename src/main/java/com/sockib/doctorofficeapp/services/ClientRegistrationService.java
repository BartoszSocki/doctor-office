package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.RegisteredClient;
import com.sockib.doctorofficeapp.exceptions.UserAlreadyRegisteredException;
import com.sockib.doctorofficeapp.model.dto.ClientRegistrationDataDto;
import com.sockib.doctorofficeapp.repositories.ClientCredentialsRepository;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import com.sockib.doctorofficeapp.roles.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientRegistrationService {

    private ClientCredentialsRepository clientCredentialsRepository;
    private ClientInfoRepository clientInfoRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerClient(ClientRegistrationDataDto clientRegistrationDataDto) {
        var username = clientRegistrationDataDto.getUsername();
        var client = clientCredentialsRepository.findRegisteredClientByUsername(username);
        client.ifPresent(u -> { throw new UserAlreadyRegisteredException(username);});

        var encodedPassword = passwordEncoder.encode(clientRegistrationDataDto.getPassword());

        var registeredClient = new RegisteredClient();
        registeredClient.setUsername(username);
        registeredClient.setPassword(encodedPassword);

        clientCredentialsRepository.save(registeredClient);
    }

}
