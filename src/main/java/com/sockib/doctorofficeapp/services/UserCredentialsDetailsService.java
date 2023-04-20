package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCredentialsDetailsService implements UserDetailsService {

    private RegisteredUserRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findUserAuthByUsername(username)
                .orElseThrow(() -> { throw new UsernameNotFoundException(username); });
    }
}
