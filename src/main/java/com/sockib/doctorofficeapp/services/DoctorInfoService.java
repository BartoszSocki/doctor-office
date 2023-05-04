package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import com.sockib.doctorofficeapp.entities.DoctorInfo;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor

@Service
public class DoctorInfoService {

    private DoctorInfoRepository doctorInfoRepository;

    public DoctorInfo getDoctorInfo(String username) {
        log.trace("getting doctor info by username: " + username);

        return doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public DoctorInfo getDoctorInfo(Long id) {
        log.trace("getting doctor info by id: " + id);

        return doctorInfoRepository.findDoctorInfoById(id)
                .orElseThrow(() -> new RuntimeException("TODO"));
    }

}
