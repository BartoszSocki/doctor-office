package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.RegisteredDoctor;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScheduledVisitRepositoryUnitTests {

    @Autowired
    ScheduledVisitRepository scheduledVisitRepository;

    @Test
    void givenScheduledVisit_whenSave_thenReturnSavedScheduledVisit() {
        // given
        var registeredDoctor = new RegisteredDoctor();
        registeredDoctor.setUsername("bob");
        registeredDoctor.setPassword("xxx");

        var scheduledVisit = new ScheduledVisit();
        scheduledVisit.setLocalization("aaa");
        scheduledVisit.setPrice(100);
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.THU);
        scheduledVisit.setType("bbb");
        scheduledVisit.setVisitBegTime(LocalTime.of(10, 20));
        scheduledVisit.setVisitEndTime(LocalTime.of(11, 20));
        scheduledVisit.setRegisteredDoctor(registeredDoctor);

        // when
        scheduledVisitRepository.save(scheduledVisit);

        // then
        var queriedScheduledVisit = scheduledVisitRepository.findScheduledVisitByType("bbb").get();

        scheduledVisit.setId(0L);
        queriedScheduledVisit.setId(0L);

        assertEquals(scheduledVisit, queriedScheduledVisit);
    }
}