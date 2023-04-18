package com.example.springauthapi.services;

import com.example.springauthapi.entities.UserAuth;
import com.example.springauthapi.exceptions.UserAlreadyRegisteredException;
import com.example.springauthapi.model.dto.UserRegisterDataDto;
import com.example.springauthapi.model.mappers.UserRegisterDataDtoUserMapper;
import com.example.springauthapi.repositories.UserAuthRepository;
import com.example.springauthapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private UserRepository userRepository;
    private UserAuthRepository userAuthRepository;
    private PasswordEncoder passwordEncoder;

    // TODO validate data
    @Transactional
    public void registerUser(UserRegisterDataDto userData) {
        var user = userRepository.findByUsername(userData.getUsername());
        user.ifPresent(u -> { throw new UserAlreadyRegisteredException(u.getUsername());});

        // hash the password
        var encodedPassword = passwordEncoder.encode(userData.getPassword());
        var username = userData.getUsername();

        // store user in db
        var userMapper = new UserRegisterDataDtoUserMapper();
        userRepository.save(userMapper.mapToUser(userData));

        // store password in db
        var userAuth = new UserAuth();
        userAuth.setUsername(username);
        userAuth.setEncodedPassword(encodedPassword);

        userAuthRepository.save(userAuth);
    }

}
