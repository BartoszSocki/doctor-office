package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.RegisteredClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientCredentialsRepository extends JpaRepository<RegisteredClient, Long> {

    Optional<RegisteredClient> findRegisteredClientByUsername(String username);

}
