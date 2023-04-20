package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.DoctorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorInfoRepository extends JpaRepository<DoctorInfo, Long> {

    Optional<DoctorInfo> findDoctorInfoByUsername(String username);

}
