package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduledVisitsRepository extends JpaRepository<ScheduledVisit, Long> {

    @Query("SELECT v FROM ScheduledVisit v INNER JOIN v.registeredDoctor d WHERE d.id = :id")
    List<ScheduledVisit> findScheduledVisitsByRegisteredDoctorId(Long id);

    @Query("SELECT v FROM ScheduledVisit v INNER JOIN v.registeredDoctor d " +
            "WHERE d.id = :id AND v.disabled = false")
    List<ScheduledVisit> findEnabledScheduledVisitsByDoctorId(Long id);

    @Query("SELECT v FROM ScheduledVisit v INNER JOIN v.registeredDoctor d " +
            "WHERE d.username = :username AND v.disabled = false")
    List<ScheduledVisit> findEnabledScheduledVisitsByDoctorUsername(String username);

    @Query("SELECT v FROM ScheduledVisit v INNER JOIN v.registeredDoctor d " +
            "WHERE v.id = :visitId " +
            "AND d.username = :username")
    Optional<ScheduledVisit> findByIdAndDoctorUsername(Long visitId, String username);

    @Query("SELECT v FROM ScheduledVisit v INNER JOIN v.registeredDoctor d " +
            "WHERE d.username = :username " +
            "AND v.dayOfTheWeek = :day " +
            "AND (:beg <= v.visitBegTime AND v.visitBegTime <= :end) " +
            "OR (:beg <= v.visitEndTime AND v.visitEndTime <= :end)")
    List<ScheduledVisit> findByTimeIntervalAndDoctorUsername(String username, LocalTime beg, LocalTime end, DayOfTheWeek day);
}
