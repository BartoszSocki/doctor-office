package com.sockib.doctorofficeapp.config;

import com.sockib.doctorofficeapp.roles.Role;
import com.sockib.doctorofficeapp.exceptions.handlers.CustomAccessDeniedHandler;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain basicSecurity0(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.securityMatcher("/token", "/error");
        http.authorizeHttpRequests(a -> {
            a.requestMatchers("/token").permitAll();
            a.requestMatchers("/error").permitAll();
        });
        http.httpBasic();
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public SecurityFilterChain basicSecurity1(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests(a -> {
            a.requestMatchers("/api/client/register").permitAll();
            a.requestMatchers("/api/doctor/register").permitAll();
            a.requestMatchers("/api/client/**").hasRole(Role.CLIENT.value());
            a.requestMatchers("/api/doctor/**").hasRole(Role.DOCTOR.value());
            a.anyRequest().authenticated();
        });
        http.headers(h -> h.frameOptions().sameOrigin());
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey key) throws JOSEException {
        var decoder = NimbusJwtDecoder.withPublicKey(key.toRSAPublicKey()).build();

        var jwtTimestampValidator = new JwtTimestampValidator(Duration.ofSeconds(60));
        decoder.setJwtValidator(jwtTimestampValidator);

        return decoder;
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> source) {
        return new NimbusJwtEncoder(source);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("role");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public KeyPair keyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Bean
    public RSAKey rsaKey(KeyPair keyPair) {
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey key) {
        var set = new JWKSet(key);

        return (jwkSelector, securityContext) -> jwkSelector.select(set);
    }

}
