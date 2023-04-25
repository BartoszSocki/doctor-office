package com.sockib.doctorofficeapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.Role;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.model.dto.UserLoginDataDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class DoctorScheduledVisitsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String clientToken;
    String doctorToken;

     MockHttpServletResponse sendForm(ScheduledVisitFormDto scheduledVisitFormDto) throws Exception {
        var headers = new HttpHeaders();
        headers.setBearerAuth(doctorToken);

        var content = objectMapper.writeValueAsString(scheduledVisitFormDto);

        return mockMvc.perform(post("/api/doctor/scheduled-visits")
                 .headers(headers)
                 .content(content)
                 .contentType(MediaType.APPLICATION_JSON))
                 .andReturn().getResponse();
    }

    // TODO solve login issue
    @BeforeEach
    void setup() throws Exception {
        var doctorLogin = new UserLoginDataDto(Role.DOCTOR.value(), "seba", "pass");
        doctorToken = mockMvc.perform(post("/token")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(doctorLogin)))
                .andReturn().getResponse().getContentAsString();

        var clientLogin = new UserLoginDataDto(Role.CLIENT.value(), "bart", "pass");
        clientToken = mockMvc.perform(post("/token")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(clientLogin)))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void givenValidScheduledVisit_whenAdded_thenSucceed() throws Exception {
        // given
        var formData = new ScheduledVisitFormDto();
        formData.setDayOfTheWeek("MON");
        formData.setVisitBegTime(LocalTime.of(10, 0));
        formData.setVisitEndTime(LocalTime.of(11, 0));
        formData.setPrice(250);
        formData.setLocalization("second");
        formData.setType("bbb");

        // when
        var res = sendForm(formData);

        // then
        Assertions.assertEquals(HttpStatus.OK.value(), res.getStatus());
    }

    @Test
    void givenInvalidScheduledVisit_whenAdded_thenError() {

    }

    @Test
    void givenValidScheduledVisitId_whenRemoved_thenSucceed() throws Exception {
        // given
        var formData = new ScheduledVisitFormDto();
        formData.setDayOfTheWeek("MON");
        formData.setVisitBegTime(LocalTime.of(10, 0));
        formData.setVisitEndTime(LocalTime.of(11, 0));
        formData.setPrice(250);
        formData.setLocalization("first");
        formData.setType("bbb");

        sendForm(formData);

        var headers = new HttpHeaders();
        headers.setBearerAuth(doctorToken);

        // TODO extract doctor id
        var getScheduledVisitsResponse = mockMvc.perform(get("/api/doctor/10/scheduled-visits")
                .headers(headers))
                .andReturn()
                .getResponse();

        var visits = (List) objectMapper.readerFor(List.class).readValue(getScheduledVisitsResponse.getContentAsString());
        var visitId = (int) ((HashMap<String, Object>) visits.get(0)).get("id");

        var deleteResponse = mockMvc.perform(delete("/api/doctor/scheduled-visits/" + visitId)
                .headers(headers))
                .andReturn()
                .getResponse();

        var getResponse = mockMvc.perform(get("/api/doctor/10//scheduled-visits/" + visitId)
                .headers(headers))
                .andReturn()
                .getResponse();

        // then
        Assertions.assertEquals("", getResponse.getContentAsString());
        Assertions.assertEquals(HttpStatus.OK.value(), deleteResponse.getStatus());
    }

    @Test
    void givenInvalidScheduledVisitId_whenRemoved_thenError() {

    }
}