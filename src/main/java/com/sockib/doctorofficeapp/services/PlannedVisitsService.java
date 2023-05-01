package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PlannedVisitsService {

//    private ScheduledVisitsRepository scheduledVisitsRepository;
//    private PlannedVisitsRepository plannedVisitsRepository;
//    private ClientCredentialsRepository clientCredentialsRepository;
//    private DoctorCredentialsRepository doctorCredentialsRepository;
//
//     TODO error when username not found?
//    public List<PlannedVisit> getClientPlannedVisits(String username) {
//        var registeredClient = clientCredentialsRepository.findRegisteredClientByUsername(username)
//                .orElseThrow(() -> new RuntimeException("TODO"));
//        return plannedVisitsRepository.findPlannedVisitsByClientId(registeredClient.getId());
//    }
//
//     TODO no error when username not found?
//    public List<PlannedVisit> getDoctorPlannedVisits(String username) {
//        return plannedVisitsRepository.findPlannedVisitsByDoctorUsername(username);
//    }
//
//    public void requestPlannedVisit(String username, Long scheduledVisitId, LocalDate date) {
//        var scheduledVisit = scheduledVisitsRepository.findById(scheduledVisitId)
//                .orElseThrow(() -> new RuntimeException("TODO"));
//
//        var registeredClient = clientCredentialsRepository.findRegisteredClientByUsername(username)
//                .orElseThrow(() -> new RuntimeException("TODO"));
//
//        var registeredDoctor = doctorCredentialsRepository.findRegisteredDoctorByUsername(username)
//                .orElseThrow(() -> new RuntimeException("TODO"));
//
//        var time = scheduledVisit.getVisitBegTime();
//        var day = LocalDateTime.of(date, time);
//
//        var plannedVisit = new PlannedVisit();
//        plannedVisit.setScheduledVisit(scheduledVisit);
//        plannedVisit.setRegisteredDoctor(registeredDoctor);
//        plannedVisit.setRegisteredUser(registeredClient);
//        plannedVisit.setDay(day);
//
//        plannedVisitsRepository.save(plannedVisit);
//    }
//
//    public void cancelDoctorPlannedVisit(Long visitId, String username) {
//        var registeredDoctor = doctorCredentialsRepository.findRegisteredDoctorByUsername(username)
//                .orElseThrow(() -> new RuntimeException("TODO"));
//
//        var plannedVisit = plannedVisitsRepository.findById(visitId)
//                .orElseThrow(() -> new RuntimeException("TOOD"));
//
//        if (!plannedVisit.getRegisteredDoctor().getId().equals(registeredDoctor.getId())) {
//            throw new RuntimeException("TOOD");
//        }
//
//        cancelVisit(visitId);
//         TODO send message to client
//    }
//
//    public void cancelClientPlannedVisit(Long visitId, String username) {
//        var registeredClient = clientCredentialsRepository.findRegisteredClientByUsername(username)
//                .orElseThrow(() -> new RuntimeException("TODO"));
//
//        var plannedVisit = plannedVisitsRepository.findById(visitId)
//                .orElseThrow(() -> new RuntimeException("TOOD"));
//
//        if (!plannedVisit.getRegisteredUser().getId().equals(registeredClient.getId())) {
//            throw new RuntimeException("TOOD");
//        }
//
//        cancelVisit(visitId);
//         TODO send message to doctor
//    }
//
//    private void cancelVisit(Long visitId) {
//        var visit = plannedVisitsRepository.findById(visitId)
//                .orElseThrow(() -> new RuntimeException("TODO"));
//        visit.setCancelled(true);
//        plannedVisitsRepository.save(visit);
//    }
//
}
