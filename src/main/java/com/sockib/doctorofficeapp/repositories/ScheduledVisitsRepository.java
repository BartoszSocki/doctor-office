package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduledVisitsRepository extends JpaRepository<ScheduledVisit, Long> {

    @Query("SELECT v FROM ScheduledVisit v WHERE v.registeredDoctor.id = :id and v.dayOfTheWeek = :dayOfTheWeek")
    List<ScheduledVisit> findScheduledVisitsByDayOfTheWeekAndRegisteredDoctor(
            DayOfTheWeek dayOfTheWeek, Long id);

    @Query("SELECT v FROM ScheduledVisit v WHERE v.registeredDoctor.id = :id")
    List<ScheduledVisit> findScheduledVisitsByRegisteredDoctorId(Long id);
}
