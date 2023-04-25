package com.sockib.doctorofficeapp.services;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.repositories.ClientCredentialsRepository;
import com.sockib.doctorofficeapp.repositories.PlannedVisitsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class PlannedVisitsService {

    private PlannedVisitsRepository plannedVisitsRepository;
    private ClientCredentialsRepository clientCredentialsRepository;

    public List<PlannedVisit> getClientPlannedVisits(Principal principal) {
        var registeredClient = clientCredentialsRepository.findRegisteredClientByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("TODO"));
        return plannedVisitsRepository.findPlannedVisitsByClientId(registeredClient.getId());
    }

    public List<PlannedVisit> getDoctorPlannedVisits(Long doctorId) {
        return plannedVisitsRepository.findPlannedVisitsByDoctorId(doctorId);
    }

    public void signUpClientForPlannedVisit(String username, Long visitId) {
        var visit = plannedVisitsRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        var registeredClient = clientCredentialsRepository.findRegisteredClientByUsername(username)
                .orElseThrow(() -> new RuntimeException("TODO"));

        visit.setRegisteredClient(registeredClient);
        plannedVisitsRepository.save(visit);
    }

    public void cancelDoctorPlannedVisit(Long visitId) {
        cancelVisit(visitId);
        // TODO send message to client
    }

    public void cancelClientPlannedVisit(Long visitId) {
        cancelVisit(visitId);
        // TODO send message to doctor
    }

    private void cancelVisit(Long visitId) {
        var visit = plannedVisitsRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));
        visit.setCancelled(true);
        plannedVisitsRepository.save(visit);
    }

}
