package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.exceptions.UserNotFoundException;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class RegisteredUserDetailsService implements UserDetailsService {

    private RegisteredUserRepository registeredUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user " + username + " not found"));
    }
}
