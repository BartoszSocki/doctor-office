package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long> {

    @Query("SELECT i FROM ClientInfo i INNER JOIN i.registeredUser u WHERE u.username = :username")
    Optional<ClientInfo> findClientInfoByUsername(String username);

    @Query("SELECT i FROM ClientInfo i INNER JOIN i.registeredUser u WHERE u.id = :id")
    Optional<ClientInfo> findClientInfoById(Long id);

}
