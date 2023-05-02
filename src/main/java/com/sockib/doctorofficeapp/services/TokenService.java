package com.sockib.doctorofficeapp.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class TokenService {

    private JwtEncoder jwtEncoder;

    public String issueToken(UserDetails details) {
        var sub = details.getUsername();
        var role = details.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var iss = Instant.now();
        var exp = iss.plusSeconds(60 * 60);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .claim("sub", sub)
                .claim("role", role)
                .claim("iss", "self")
                .claim("iat", iss.getEpochSecond())
                .claim("exp", exp.getEpochSecond())
                .build();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

}
