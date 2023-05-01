package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlannedVisitsRepository extends JpaRepository<PlannedVisit, Long> {

    Optional<PlannedVisit> findPlannedVisitByCancelled(boolean cancelled);

    @Query("SELECT v FROM PlannedVisit v WHERE v.registeredClient.id = :clientId")
    List<PlannedVisit> findPlannedVisitsByClientId(Long clientId);

    @Query("SELECT v FROM PlannedVisit v WHERE v.registeredDoctor.id = :doctorId")
    List<PlannedVisit> findPlannedVisitsByDoctorId(Long doctorId);

    @Query("SELECT v FROM PlannedVisit v WHERE v.registeredDoctor.username = :username")
    List<PlannedVisit> findPlannedVisitsByDoctorUsername(String username);

}
