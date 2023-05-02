package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.repositories.ClientInfoRepository;
import com.sockib.doctorofficeapp.repositories.DoctorInfoRepository;
import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor

@Service
public class PlannedVisitsService {

    private ScheduledVisitsRepository scheduledVisitsRepository;
    private PlannedVisitsRepository plannedVisitsRepository;

    private ClientInfoRepository clientInfoRepository;
    private DoctorInfoRepository doctorInfoRepository;

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

    public void cancelDoctorPlannedVisit(Long visitId, String username) {
        var doctorInfo = doctorInfoRepository.findDoctorInfoByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var plannedVisit = plannedVisitsRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        if (!plannedVisit.getRegisteredDoctor().getId().equals(doctorInfo.getRegisteredUser().getId())) {
            throw new RuntimeException("TODO");
        }

        cancelVisit(plannedVisit);
//         TODO send message to client
    }

    public void cancelClientPlannedVisit(Long visitId, String username) {
        var registeredClient = clientInfoRepository.findClientInfoByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var plannedVisit = plannedVisitsRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        if (!plannedVisit.getRegisteredClient().getId().equals(registeredClient.getRegisteredUser().getId())) {
            throw new RuntimeException("TODO");
        }

        cancelVisit(plannedVisit);
//         TODO send message to doctor
    }

    private void cancelVisit(PlannedVisit plannedVisit) {
        plannedVisit.setCancelled(true);
        plannedVisitsRepository.save(plannedVisit);
    }

}
