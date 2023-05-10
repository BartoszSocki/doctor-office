package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.DoctorInfo;
import com.sockib.doctorofficeapp.entities.RegisteredUser;
import com.sockib.doctorofficeapp.exceptions.UserNotFoundException;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor

@Service
public class DoctorInfoService {

    private DoctorInfoRepository doctorInfoRepository;
    private PlannedVisitsRepository plannedVisitsRepository;

    public DoctorInfo getDoctorInfo(String username) {
        log.trace("getting doctor info by username: " + username);

        return doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("username: " + username));
    }

    public DoctorInfo getDoctorInfo(Long id) {
        log.trace("getting doctor info by id: " + id);

        return doctorInfoRepository.findDoctorInfoById(id)
                .orElseThrow(() -> new UserNotFoundException("id: " + id));
    }

    public List<RegisteredUser> getDoctorClients(String username) {
        return plannedVisitsRepository.findDoctorClients(username);
    }

}
