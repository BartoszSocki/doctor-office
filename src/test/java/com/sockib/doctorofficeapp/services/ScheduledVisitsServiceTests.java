package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.RegisteredUser;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ScheduledVisitsServiceTests {

    ScheduledVisitsService scheduledVisitsService;
    RegisteredUserRepository registeredUserRepository;
    ScheduledVisitsRepository scheduledVisitRepository;
    ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        registeredUserRepository = mock(RegisteredUserRepository.class);
        scheduledVisitRepository = mock(ScheduledVisitsRepository.class);
        modelMapper = new ModelMapper();
        scheduledVisitsService = new ScheduledVisitsService(registeredUserRepository, scheduledVisitRepository, modelMapper);

        when(scheduledVisitRepository.save(any(ScheduledVisit.class))).thenAnswer(
                inv -> inv.getArgument(0));
    }

    @Test
    void givenNewVisit_whenCreateNew_thenCreateNewScheduledVisit() {
        // given
        var scheduledVisitFormDto = new ScheduledVisitFormDto(
                "MON",
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                100,
                "aaa",
                "bbb"
        );

        when(registeredUserRepository.findRegisteredUserByUsername(any(String.class))).thenAnswer(
                inv -> Optional.of(new RegisteredUser()));

        // when
        var scheduledVisit1 = scheduledVisitsService.createScheduledVisit(scheduledVisitFormDto, "bob");
        scheduledVisit1.setRegisteredDoctor(null);

        // then
        var scheduledVisit2 = modelMapper.map(scheduledVisitFormDto, ScheduledVisit.class);
        assertEquals(scheduledVisit2, scheduledVisit1);
    }

    @Test
    void givenNonExistingUser_whenCreateNew_thenThrowException() {
        // given
        var scheduledVisitFormDto = new ScheduledVisitFormDto();

        // when
        when(registeredUserRepository.findRegisteredUserByUsername(any(String.class))).thenAnswer(inv -> null);

        // then
        assertThrows(RuntimeException.class, () ->
                scheduledVisitsService.createScheduledVisit(scheduledVisitFormDto, "bob"));
    }

}