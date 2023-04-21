package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.RegisteredDoctor;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduledVisitRepository extends JpaRepository<ScheduledVisit, Long> {

    Optional<ScheduledVisit> findScheduledVisitByDayOfTheWeekAndRegisteredDoctor(
            DayOfTheWeek dayOfTheWeek, RegisteredDoctor registeredDoctor);

    Optional<ScheduledVisit> findScheduledVisitByType(String type);
}
