package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.exceptions.UserAlreadyRegisteredException;
import com.sockib.doctorofficeapp.model.dto.DoctorRegistrationDataDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//@AllArgsConstructor
public class DoctorRegistrationService {

//    private DoctorCredentialsRepository doctorCredentialsRepository;
//    private PasswordEncoder passwordEncoder;
//
//    @Transactional
//    public void registerDoctor(DoctorRegistrationDataDto doctorRegistrationDataDto) {
//        var username = doctorRegistrationDataDto.getUsername();
//        var doctor = doctorCredentialsRepository.findRegisteredDoctorByUsername(username);
//        doctor.ifPresent(u -> { throw new UserAlreadyRegisteredException(username);});
//
//        var encodedPassword = passwordEncoder.encode(doctorRegistrationDataDto.getPassword());
//
//        var registeredDoctor = new RegisteredDoctor();
//        registeredDoctor.setUsername(username);
//        registeredDoctor.setPassword(encodedPassword);
//
//        doctorCredentialsRepository.save(registeredDoctor);
//    }

}
