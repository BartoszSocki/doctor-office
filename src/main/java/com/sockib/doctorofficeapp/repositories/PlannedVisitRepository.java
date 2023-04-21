package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlannedVisitRepository extends JpaRepository<PlannedVisit, Long> {

    Optional<PlannedVisit> findPlannedVisitByCancelled(boolean cancelled);
}
