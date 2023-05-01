package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long> {

//    Optional<ClientInfo> findClientInfoByUsername(String username);

}
