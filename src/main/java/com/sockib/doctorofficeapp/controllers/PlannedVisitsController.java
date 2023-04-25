package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.services.PlannedVisitsService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class PlannedVisitsController {

    private PlannedVisitsService plannedVisitsService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/client/planned-visits")
    public List<PlannedVisit> getClientPlannedVisits(Principal principal) {
        return plannedVisitsService.getClientPlannedVisits(principal);
    }

    @GetMapping(path = "/doctor/{doctorId}/planned-visits")
    public List<PlannedVisit> getDoctorPlannedVisits(@PathVariable Long doctorId) {
        return plannedVisitsService.getDoctorPlannedVisits(doctorId);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(path = "/doctor/planned-visits/{visitId}")
    public void signUpForPlannedVisit(Principal principal, @PathVariable Long visitId) {
        plannedVisitsService.signUpClientForPlannedVisit(principal.getName(), visitId);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/doctor/planned-visits/{visitId}")
    public void cancelDoctorPlannedVisit(@PathVariable Long visitId) {
        plannedVisitsService.cancelDoctorPlannedVisit(visitId);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping(path = "/client/planned-visits/{visitId}")
    public void cancelClientPlannedVisit(@PathVariable Long visitId) {
        plannedVisitsService.cancelClientPlannedVisit(visitId);
    }

}
