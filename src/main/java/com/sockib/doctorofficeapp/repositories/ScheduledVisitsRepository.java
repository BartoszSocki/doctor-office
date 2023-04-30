package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScheduledVisitsRepository extends JpaRepository<ScheduledVisit, Long> {

    @Query("SELECT v FROM ScheduledVisit v WHERE v.registeredDoctor.id = :id and v.dayOfTheWeek = :dayOfTheWeek")
    List<ScheduledVisit> findScheduledVisitsByDayOfTheWeekAndRegisteredDoctor(
            DayOfTheWeek dayOfTheWeek, Long id);

    @Query("SELECT v FROM ScheduledVisit v WHERE v.registeredDoctor.id = :id")
    List<ScheduledVisit> findScheduledVisitsByRegisteredDoctorId(Long id);

    @Query("SELECT v FROM ScheduledVisit v WHERE v.id = :visitId AND v.registeredDoctor.username = :username")
    Optional<ScheduledVisit> findByIdAndDoctorUsername(Long visitId, String username);

    // TODO rewrite it
    @Modifying
    void deleteScheduledVisitByIdAndRegisteredDoctorUsername(Long scheduledVisitId, String registeredDoctorUsername);
//    @Query("DELETE FROM ScheduledVisit v WHERE v.id = :visitId AND v.registeredDoctor.username = :username")
//    void deleteByIdAndDoctorUsername(Long visitId, String username);

}
