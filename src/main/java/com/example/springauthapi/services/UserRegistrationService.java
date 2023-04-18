package com.example.springauthapi.services;

import com.example.springauthapi.entities.UserAuth;
import com.example.springauthapi.exceptions.UserAlreadyRegisteredException;
import com.example.springauthapi.model.dto.UserRegisterDataDto;
import com.example.springauthapi.repositories.UserAuthRepository;
import com.example.springauthapi.roles.Roles;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private UserAuthRepository userAuthRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserRegisterDataDto userData) {
        var user = userAuthRepository.findUserAuthByUsername(userData.getUsername());
        user.ifPresent(u -> { throw new UserAlreadyRegisteredException(u.getUsername());});

        var encodedPassword = passwordEncoder.encode(userData.getPassword());
        var username = userData.getUsername();

        var userAuth = new UserAuth();
        userAuth.setUsername(username);
        userAuth.setEncodedPassword(encodedPassword);
        userAuth.setRole(Roles.USER.value());

        userAuthRepository.save(userAuth);
    }

}
