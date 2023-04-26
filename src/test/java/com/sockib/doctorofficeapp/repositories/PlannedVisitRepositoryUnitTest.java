package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.entities.RegisteredClient;
import com.sockib.doctorofficeapp.entities.RegisteredDoctor;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlannedVisitRepositoryUnitTest {

    @Autowired
    PlannedVisitsRepository plannedVisitRepository;

    @Test
    void givenPlannedVisit_whenSave_thenReturnSavedPlannedVisit() {
        // given
        var registeredDoctor = new RegisteredDoctor();
        registeredDoctor.setUsername("bob");
        registeredDoctor.setPassword("xxx");

        var registeredClient = new RegisteredClient();
        registeredClient.setUsername("john");
        registeredClient.setPassword("xxx");

        var scheduledVisit = new ScheduledVisit();
        scheduledVisit.setLocalization("aaa");
        scheduledVisit.setPrice(100);
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.THU);
        scheduledVisit.setType("bbb");
        scheduledVisit.setVisitBegTime(LocalTime.of(10, 20));
        scheduledVisit.setVisitEndTime(LocalTime.of(11, 20));
        scheduledVisit.setRegisteredDoctor(registeredDoctor);

        var plannedVisit = new PlannedVisit();
        plannedVisit.setDay(LocalDateTime.now());
        plannedVisit.setRegisteredClient(registeredClient);
        plannedVisit.setRegisteredDoctor(registeredDoctor);
        plannedVisit.setScheduledVisit(scheduledVisit);

        // when
        plannedVisitRepository.save(plannedVisit);

        // then
        var queriedPlannedVisit = plannedVisitRepository.findPlannedVisitByCancelled(false).get();

        scheduledVisit.setId(0L);
        queriedPlannedVisit.setId(0L);

        assertEquals(plannedVisit, queriedPlannedVisit);
    }
}