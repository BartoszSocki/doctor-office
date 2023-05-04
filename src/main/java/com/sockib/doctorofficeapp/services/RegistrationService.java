package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import com.sockib.doctorofficeapp.entities.DoctorInfo;
import com.sockib.doctorofficeapp.entities.RegisteredUser;
import com.sockib.doctorofficeapp.enums.Role;
import com.sockib.doctorofficeapp.exceptions.UserAlreadyRegisteredException;
import com.sockib.doctorofficeapp.model.dto.ClientRegistrationDataDto;
import com.sockib.doctorofficeapp.model.dto.DoctorRegistrationDataDto;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor

@Service
public class RegistrationService {

    private RegisteredUserRepository registeredUserRepository;
    private DoctorInfoRepository doctorInfoRepository;
    private ClientInfoRepository clientInfoRepository;

    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerDoctor(DoctorRegistrationDataDto doctorRegistrationDataDto) {
        var username = doctorRegistrationDataDto.getUsername();
        var doctor = registeredUserRepository.findRegisteredUserByUsername(username);
        doctor.ifPresent(u -> { throw new UserAlreadyRegisteredException(username); });

        var encodedPassword = passwordEncoder.encode(doctorRegistrationDataDto.getPassword());
        var registeredUser = modelMapper.map(doctorRegistrationDataDto, RegisteredUser.class);
        registeredUser.setPassword(encodedPassword);
        registeredUser.setRole(Role.DOCTOR.value());

        var doctorInfo = modelMapper.map(doctorRegistrationDataDto, DoctorInfo.class);
        var persistedRegisteredUser = registeredUserRepository.save(registeredUser);
        doctorInfo.setRegisteredUser(persistedRegisteredUser);

        doctorInfoRepository.save(doctorInfo);

        log.info("successfully registered new doctor: " + username);
    }

    @Transactional
    public void registerClient(ClientRegistrationDataDto clientRegistrationDataDto) {
        var username = clientRegistrationDataDto.getUsername();
        var doctor = registeredUserRepository.findRegisteredUserByUsername(username);
        doctor.ifPresent(u -> { throw new UserAlreadyRegisteredException(username); });

        var encodedPassword = passwordEncoder.encode(clientRegistrationDataDto.getPassword());
        var registeredUser = modelMapper.map(clientRegistrationDataDto, RegisteredUser.class);
        registeredUser.setPassword(encodedPassword);
        registeredUser.setRole(Role.CLIENT.value());

        var clientInfo = modelMapper.map(clientRegistrationDataDto, ClientInfo.class);
        var persistedRegisteredUser = registeredUserRepository.save(registeredUser);
        clientInfo.setRegisteredUser(persistedRegisteredUser);

        clientInfoRepository.save(clientInfo);

        log.info("successfully registered new client: " + username);
    }

}
