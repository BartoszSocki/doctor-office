package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.RegisteredDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorCredentialsRepository extends JpaRepository<RegisteredDoctor, Long> {

    Optional<RegisteredDoctor> findRegisteredDoctorByUsername(String username);
}
