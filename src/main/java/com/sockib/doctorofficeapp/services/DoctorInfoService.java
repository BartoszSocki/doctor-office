package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.DoctorInfo;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorInfoService {

    private DoctorInfoRepository doctorInfoRepository;

    public DoctorInfo getDoctorInfo(String username) {
        return doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
