package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.exceptions.PlannedVisitAlreadyTakenException;
import com.sockib.doctorofficeapp.exceptions.UnableToGetResourceException;
import com.sockib.doctorofficeapp.exceptions.UserNotFoundException;
import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import com.sockib.doctorofficeapp.repositories.RegisteredUserRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor

@Service
public class PlannedVisitsService {

    private ScheduledVisitsRepository scheduledVisitsRepository;
    private PlannedVisitsRepository plannedVisitsRepository;
    private RegisteredUserRepository registeredUserRepository;

    private MailService mailService;

    public Page<PlannedVisit> getClientPlannedVisits(String username, Pageable pageable) {
        var registeredClient = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user " + username + " not found"));
        return plannedVisitsRepository.findPlannedVisitsByClientId(registeredClient.getId(), pageable);
    }

    public PlannedVisit getClientPlannedVisit(String username, Long visitId) {
        return plannedVisitsRepository.findPlannedVisitByClientUsernameAndVisitId(username, visitId)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate plannedVisit " + visitId + " for user " + username));
    }

    public Page<PlannedVisit> getDoctorPlannedVisits(String username, Pageable pageable) {
        var registeredDoctor = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user " + username + " not found"));
        return plannedVisitsRepository.findPlannedVisitsByDoctorId(registeredDoctor.getId(), pageable);
    }

    public PlannedVisit getDoctorPlannedVisit(String username, Long visitId) {
        return plannedVisitsRepository.findPlannedVisitByDoctorUsernameAndVisitId(username, visitId)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate plannedVisit " + visitId + " for user " + username));
    }

    // TODO check if date matches day
    public PlannedVisit requestPlannedVisit(String username, Long scheduledVisitId, LocalDate date) {
        var scheduledVisit = scheduledVisitsRepository.findById(scheduledVisitId)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate scheduledVisit " + scheduledVisitId));

        var registeredClient = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user " + username + " not found"));

        var registeredDoctor = scheduledVisit.getRegisteredDoctor();

        var time = scheduledVisit.getVisitBegTime();
        var day = LocalDateTime.of(date, time);

        var isTaken = plannedVisitsRepository.findPlannedVisitsByDoctorUsernameAndDate(registeredDoctor.getUsername(), day).stream()
                .anyMatch(v -> !v.isCancelled());

        if (isTaken) {
            throw new PlannedVisitAlreadyTakenException("visit already taken");
        }

        var plannedVisit = new PlannedVisit();
        plannedVisit.setScheduledVisit(scheduledVisit);
        plannedVisit.setRegisteredDoctor(registeredDoctor);
        plannedVisit.setRegisteredClient(registeredClient);
        plannedVisit.setDay(day);

        return plannedVisitsRepository.save(plannedVisit);
    }

    @Transactional
    public void cancelDoctorPlannedVisit(Long visitId, String username) {
        var registeredDoctor = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user " + username + " not found"));

        var plannedVisit = plannedVisitsRepository.findPlannedVisitByDoctorUsernameAndVisitId(username, visitId)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate plannedVisit " + visitId));

        if (plannedVisit.isCancelled()) {
            return;
        }

        cancelVisit(plannedVisit);
        var clientEmail = plannedVisit.getRegisteredClient().getUsername();
        var doctorName = registeredDoctor.getName();
        var doctorSurname = registeredDoctor.getSurname();
        mailService.sendMail(clientEmail, "cancellation of a visit",
                "Doctor " + doctorName + " " + doctorSurname +
                        " has cancelled your visit (" + plannedVisit.getDay().format(DateTimeFormatter.ISO_LOCAL_DATE) + ")");
    }

    @Transactional
    public void cancelClientPlannedVisit(Long visitId, String username) {
        var registeredClient = registeredUserRepository.findRegisteredUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user " + username + " not found"));

        var plannedVisit = plannedVisitsRepository.findPlannedVisitByClientUsernameAndVisitId(username, visitId)
                .orElseThrow(() -> new UnableToGetResourceException("cannot locate plannedVisit " + visitId));

        if (plannedVisit.isCancelled()) {
            return;
        }

        cancelVisit(plannedVisit);
        var doctorEmail = plannedVisit.getRegisteredDoctor().getUsername();
        var clientName = registeredClient.getName();
        var clientSurname = registeredClient.getSurname();
        mailService.sendMail(doctorEmail, "cancellation of a visit",
                "Client " + clientName + " " + clientSurname +
                        " has cancelled a visit (" + plannedVisit.getDay().format(DateTimeFormatter.ISO_LOCAL_DATE) + ")");

    }

    private void cancelVisit(PlannedVisit plannedVisit) {
        plannedVisit.setCancelled(true);
        plannedVisitsRepository.save(plannedVisit);
    }

}
