package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.RegisteredUser;
import com.sockib.doctorofficeapp.exceptions.UserAlreadyRegisteredException;
import com.sockib.doctorofficeapp.model.dto.UserCredentialsDto;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import com.sockib.doctorofficeapp.roles.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private RegisteredUserRepository userAuthRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserCredentialsDto userData) {
        var user = userAuthRepository.findUserAuthByUsername(userData.getUsername());
        user.ifPresent(u -> { throw new UserAlreadyRegisteredException(u.getUsername());});

        var encodedPassword = passwordEncoder.encode(userData.getPassword());
        var username = userData.getUsername();

        var userAuth = new RegisteredUser();
        userAuth.setUsername(username);
        userAuth.setPassword(encodedPassword);
        userAuth.setRole(Role.CLIENT.value());

        userAuthRepository.save(userAuth);
    }

}
