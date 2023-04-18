package com.example.springauthapi.services;

import com.example.springauthapi.repositories.UserAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthDetailsService implements UserDetailsService {

    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findUserAuthByUsername(username)
                .orElseThrow(() -> { throw new UsernameNotFoundException(username); });
    }
}
