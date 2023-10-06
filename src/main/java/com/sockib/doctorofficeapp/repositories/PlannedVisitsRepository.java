package com.sockib.doctorofficeapp.repositories;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.entities.RegisteredUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PlannedVisitsRepository extends JpaRepository<PlannedVisit, Long> {

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredClient c WHERE c.id = :clientId")
    Page<PlannedVisit> findPlannedVisitsByClientId(Long clientId, Pageable pageable);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredDoctor d WHERE d.id = :doctorId")
    Page<PlannedVisit> findPlannedVisitsByDoctorId(Long doctorId, Pageable pageable);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredDoctor d WHERE d.username = :username AND v.id = :id")
    Optional<PlannedVisit> findPlannedVisitByDoctorUsernameAndVisitId(String username, Long id);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredClient c WHERE c.username = :username AND v.id = :id")
    Optional<PlannedVisit> findPlannedVisitByClientUsernameAndVisitId(String username, Long id);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredDoctor d WHERE d.username = :username AND v.day = :day")
    List<PlannedVisit> findPlannedVisitsByDoctorUsernameAndDate(String username, LocalDateTime day);

    @Query("SELECT v FROM PlannedVisit v WHERE v.visitStatus.canceled = false " +
            "AND v.visitStatus.canceled = false AND v.day BETWEEN :beg AND :end")
    List<PlannedVisit> findActivePlannedVisitsByInterval(LocalDateTime beg, LocalDateTime end);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredDoctor d " +
            "WHERE v.visitStatus.canceled = false " +
            "AND d.id = :doctorId " +
            "AND v.visitStatus.canceled = false AND v.day BETWEEN :beg AND :end")
    List<PlannedVisit> findDoctorActivePlannedVisitsByInterval(Long doctorId, LocalDateTime beg, LocalDateTime end);

    @Query("SELECT DISTINCT v.registeredClient FROM PlannedVisit v " +
            "INNER JOIN v.registeredDoctor d WHERE d.username = :username")
    List<RegisteredUser> findDoctorClients(String username);

    @Query("SELECT v FROM PlannedVisit v INNER JOIN v.registeredClient c INNER JOIN v.registeredDoctor d " +
            "WHERE c.id = :clientId AND d.username = :username")
    Page<PlannedVisit> findDoctorPlannedVisitsByClientId(String username, Long clientId, Pageable pageable);
}
