package com.example.springauthapi.repositories;

import com.example.springauthapi.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findUserAuthByUsername(String username);

}
