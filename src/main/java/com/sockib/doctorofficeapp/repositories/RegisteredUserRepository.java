package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

    Optional<RegisteredUser> findUserAuthByUsername(String username);

}
