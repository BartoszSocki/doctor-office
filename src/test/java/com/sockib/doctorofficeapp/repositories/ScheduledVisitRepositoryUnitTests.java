package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.RegisteredDoctor;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScheduledVisitRepositoryUnitTests {

    @Autowired
    ScheduledVisitsRepository scheduledVisitRepository;

    RegisteredDoctor registeredDoctor1;
    RegisteredDoctor registeredDoctor2;

    ScheduledVisit scheduledVisit1;
    ScheduledVisit scheduledVisit2;
    ScheduledVisit scheduledVisit3;

    @BeforeEach
    void setup() {
        registeredDoctor1 = new RegisteredDoctor();
        registeredDoctor1.setUsername("bob");
        registeredDoctor1.setPassword("xxx");

        registeredDoctor2 = new RegisteredDoctor();
        registeredDoctor2.setUsername("john");
        registeredDoctor2.setPassword("xxx");

        scheduledVisit1 = new ScheduledVisit();
        scheduledVisit1.setLocalization("aaa");
        scheduledVisit1.setPrice(100);
        scheduledVisit1.setDayOfTheWeek(DayOfTheWeek.THU);
        scheduledVisit1.setType("bbb");
        scheduledVisit1.setVisitBegTime(LocalTime.of(10, 20));
        scheduledVisit1.setVisitEndTime(LocalTime.of(11, 20));
        scheduledVisit1.setRegisteredDoctor(registeredDoctor1);

        scheduledVisit2 = new ScheduledVisit();
        scheduledVisit2.setLocalization("aaa");
        scheduledVisit2.setPrice(100);
        scheduledVisit2.setDayOfTheWeek(DayOfTheWeek.MON);
        scheduledVisit2.setType("bbb");
        scheduledVisit2.setVisitBegTime(LocalTime.of(10, 20));
        scheduledVisit2.setVisitEndTime(LocalTime.of(11, 20));
        scheduledVisit2.setRegisteredDoctor(registeredDoctor1);

        scheduledVisit3 = new ScheduledVisit();
        scheduledVisit3.setLocalization("bbb");
        scheduledVisit3.setPrice(100);
        scheduledVisit3.setDayOfTheWeek(DayOfTheWeek.WED);
        scheduledVisit3.setType("bbb");
        scheduledVisit3.setVisitBegTime(LocalTime.of(10, 20));
        scheduledVisit3.setVisitEndTime(LocalTime.of(11, 20));
        scheduledVisit3.setRegisteredDoctor(registeredDoctor2);
    }

    @Test
    void givenScheduledVisit_whenSave_thenReturnSavedScheduledVisit() {
        // given
        // when
        var savedVisit = scheduledVisitRepository.save(scheduledVisit1);

        // then
        var queriedScheduledVisit = scheduledVisitRepository.findById(savedVisit.getId()).get();

        queriedScheduledVisit.setId(0L);

        assertEquals(scheduledVisit1, savedVisit);
    }

    @Test
    void givenRegisteredDoctorId_whenQuerying_thenReturnScheduledVisits() {
        // given
        var saved1 = scheduledVisitRepository.save(scheduledVisit1);
        var saved2 = scheduledVisitRepository.save(scheduledVisit2);
        var doctorId = registeredDoctor1.getId();
        var expectedLength = 2;

        // when
        var visits = scheduledVisitRepository.findScheduledVisitsByRegisteredDoctorId(doctorId);

        // then
        assertTrue(visits.contains(saved1));
        assertTrue(visits.contains(saved2));

        assertEquals(expectedLength, visits.size());
    }
}