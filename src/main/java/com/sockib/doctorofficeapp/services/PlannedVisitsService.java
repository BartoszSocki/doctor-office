package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor

@Service
public class PlannedVisitsService {

    private ScheduledVisitsRepository scheduledVisitsRepository;
    private PlannedVisitsRepository plannedVisitsRepository;
    private ClientInfoRepository clientInfoRepository;
    private DoctorInfoRepository doctorInfoRepository;

    private MailService mailService;

    public List<PlannedVisit> getClientPlannedVisits(String username) {
        var clientInfo = clientInfoRepository.findClientInfoByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));
        return plannedVisitsRepository.findPlannedVisitsByClientId(clientInfo.getId());
    }

    public List<PlannedVisit> getDoctorPlannedVisits(String username) {
        var doctorInfo = doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));
        return plannedVisitsRepository.findPlannedVisitsByDoctorId(doctorInfo.getId());
    }

    public void requestPlannedVisit(String username, Long scheduledVisitId, LocalDate date) {
        var scheduledVisit = scheduledVisitsRepository.findById(scheduledVisitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var registeredClient = clientInfoRepository.findClientInfoByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var registeredDoctor = doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var time = scheduledVisit.getVisitBegTime();
        var day = LocalDateTime.of(date, time);

        var plannedVisit = new PlannedVisit();
        plannedVisit.setScheduledVisit(scheduledVisit);
        plannedVisit.setRegisteredDoctor(registeredDoctor.getRegisteredUser());
        plannedVisit.setRegisteredClient(registeredClient.getRegisteredUser());
        plannedVisit.setDay(day);

        plannedVisitsRepository.save(plannedVisit);
    }

    @Transactional
    public void cancelDoctorPlannedVisit(Long visitId, String username) {
        var doctorInfo = doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var plannedVisit = plannedVisitsRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        if (!plannedVisit.getRegisteredDoctor().getId().equals(doctorInfo.getRegisteredUser().getId())) {
            throw new RuntimeException("TODO");
        }

        cancelVisit(plannedVisit);
        var clientEmail = plannedVisit.getRegisteredClient().getUsername();
        var doctorName = doctorInfo.getRegisteredUser().getName();
        var doctorSurname = doctorInfo.getRegisteredUser().getSurname();
        mailService.sendMail(clientEmail, "cancellation of a visit",
                "Doctor " + doctorName + " " + doctorSurname +
                        " has cancelled your visit (" + plannedVisit.getDay().format(DateTimeFormatter.ISO_LOCAL_DATE) + ")");
    }

    @Transactional
    public void cancelClientPlannedVisit(Long visitId, String username) {
        var clientInfo = clientInfoRepository.findClientInfoByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var plannedVisit = plannedVisitsRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        if (!plannedVisit.getRegisteredClient().getId().equals(clientInfo.getRegisteredUser().getId())) {
            throw new RuntimeException("TODO");
        }

        cancelVisit(plannedVisit);
        var doctorEmail = plannedVisit.getRegisteredDoctor().getUsername();
        var clientName = clientInfo.getRegisteredUser().getName();
        var clientSurname = clientInfo.getRegisteredUser().getSurname();
        mailService.sendMail(doctorEmail, "cancellation of a visit",
                "Client " + clientName + " " + clientSurname +
                        " has cancelled a visit (" + plannedVisit.getDay().format(DateTimeFormatter.ISO_LOCAL_DATE) + ")");

    }

    private void cancelVisit(PlannedVisit plannedVisit) {
        plannedVisit.setCancelled(true);
        plannedVisitsRepository.save(plannedVisit);
    }

}
