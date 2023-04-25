package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.entities.RegisteredClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlannedVisitsRepository extends JpaRepository<PlannedVisit, Long> {

    Optional<PlannedVisit> findPlannedVisitByCancelled(boolean cancelled);

    @Query("SELECT v FROM PlannedVisit v WHERE v.registeredClient.id = :clientId")
    List<PlannedVisit> findPlannedVisitsByClientId(Long clientId);

    @Query("SELECT v FROM PlannedVisit v WHERE v.registeredDoctor.id = :doctorId")
    List<PlannedVisit> findPlannedVisitsByDoctorId(Long doctorId);

//    @Modifying
//    @Query("UPDATE PlannedVisit v SET v.registeredClient = :registeredClient WHERE v.id = :visitId")
//    void updateRegisteredClient(RegisteredClient registeredClient, Long visitId);

}
