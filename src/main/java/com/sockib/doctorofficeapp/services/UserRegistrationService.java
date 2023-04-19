package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.UserAuth;
import com.sockib.doctorofficeapp.exceptions.UserAlreadyRegisteredException;
import com.sockib.doctorofficeapp.model.dto.UserRegisterDataDto;
import com.sockib.doctorofficeapp.repositories.UserAuthRepository;
import com.sockib.doctorofficeapp.roles.Roles;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
