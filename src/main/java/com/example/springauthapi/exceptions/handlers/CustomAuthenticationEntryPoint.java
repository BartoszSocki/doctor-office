package com.example.springauthapi.exceptions.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jfr.ContentType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.Instant;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        var mapper = new ObjectMapper();
        var ex = new AuthErrorWrapper(authException);

//        var exstr = ResponseEntity.badRequest().body(ex).getBody();
//        log.info(exstr);

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().print(mapper.writeValueAsString(ex));
    }

    @Getter
    @Setter
    private class AuthErrorWrapper {
        public final long timestamp;
        public final String message;

        public AuthErrorWrapper(AuthenticationException ex) {
            timestamp = Instant.now().getEpochSecond();
            message = ex.getMessage();
        }
    }
}
