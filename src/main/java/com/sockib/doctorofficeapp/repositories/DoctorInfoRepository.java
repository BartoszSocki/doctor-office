package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.DoctorPrivateInfo;
import com.sockib.doctorofficeapp.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorInfoRepository extends JpaRepository<DoctorPrivateInfo, Long> {

    Optional<DoctorPrivateInfo> findDoctorInfoByRegisteredUser(RegisteredUser registeredUser);

}
