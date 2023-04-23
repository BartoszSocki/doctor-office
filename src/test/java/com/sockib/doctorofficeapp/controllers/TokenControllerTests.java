package com.sockib.doctorofficeapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sockib.doctorofficeapp.config.AppConfig;
import com.sockib.doctorofficeapp.enums.Role;
import com.sockib.doctorofficeapp.model.dto.UserLoginDataDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// TODO add custom test sql script

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TokenControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtDecoder jwtDecoder;

    @Test
    void givenDoctorCredentials_whenValidInput_thenReturnJwt() throws Exception {
        // given
        var expectedUser = "seba";
        var expectedRole = Role.DOCTOR.value();
        var login = new UserLoginDataDto(expectedRole, expectedUser, "pass");

        // when
        var token = mockMvc.perform(post("/token")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)))
                .andReturn().getResponse().getContentAsString();

        // then
        var jwt = jwtDecoder.decode(token);
        assertTrue(token.length() != 0);

        var role = (String) jwt.getClaim("role");
        var user = (String) jwt.getClaim("sub");
        assertEquals(expectedRole, role);
        assertEquals(expectedUser, user);
    }

    @Test
    void givenClientCredentials_whenValidInput_thenReturnJwt() throws Exception {
        // given
        var expectedUser = "bart";
        var expectedRole = Role.CLIENT.value();
        var login = new UserLoginDataDto(expectedRole, expectedUser, "pass");

        // when
        var token = mockMvc.perform(post("/token")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(login)))
                .andReturn().getResponse().getContentAsString();

        // then
        var jwt = jwtDecoder.decode(token);
        assertTrue(token.length() != 0);

        var role = (String) jwt.getClaim("role");
        var user = (String) jwt.getClaim("sub");
        assertEquals(expectedRole, role);
        assertEquals(expectedUser, user);
    }

}