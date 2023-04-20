package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.repositories.DoctorCredentialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Qualifier("doctorDetailsService")
public class DoctorDetailsService implements UserDetailsService {

    private DoctorCredentialsRepository doctorCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorCredentialsRepository.findRegisteredDoctorByUsername(username)
                .orElseThrow(() -> { throw new UsernameNotFoundException(username); });
    }
}
