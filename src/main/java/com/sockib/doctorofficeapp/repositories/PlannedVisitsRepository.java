package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PlannedVisitsRepository extends JpaRepository<PlannedVisit, Long> {

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredClient c WHERE c.id = :clientId")
    List<PlannedVisit> findPlannedVisitsByClientId(Long clientId);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredDoctor d WHERE d.id = :doctorId")
    List<PlannedVisit> findPlannedVisitsByDoctorId(Long doctorId);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredDoctor d WHERE d.username = :username AND v.id = :id")
    Optional<PlannedVisit> findPlannedVisitByDoctorUsernameAndVisitId(String username, Long id);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredDoctor d WHERE d.username = :username AND v.day = :day")
    List<PlannedVisit> findPlannedVisitsByDoctorUsernameAndDate(String username, LocalDateTime day);

}
