package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScheduledVisitsRepository extends JpaRepository<ScheduledVisit, Long> {

    @Query("SELECT v FROM ScheduledVisit v WHERE v.registeredDoctor.id = :id")
    List<ScheduledVisit> findScheduledVisitsByRegisteredDoctorId(Long id);

    @Query("SELECT v FROM ScheduledVisit v INNER JOIN v.registeredDoctor d WHERE v.id = :visitId AND d.username = :username")
    Optional<ScheduledVisit> findByIdAndDoctorUsername(Long visitId, String username);

}
