package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("clientDetailsService")
public class ClientDetailsService implements UserDetailsService {

    private RegisteredUserRepository clientCredentialsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientCredentialsService.findRegisteredUserByUsername(username)
                .orElseThrow(() -> { throw new UsernameNotFoundException(username); });
    }
}
