package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.services.PlannedVisitsService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class PlannedVisitsController {

    private PlannedVisitsService plannedVisitsService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/client/planned-visits")
    public void getClientPlannedVisits(Principal principal) {
    }

    @GetMapping(path = "/doctor/{doctorId}/planned-visits")
    public void getDoctorPlannedVisits(@PathVariable Long doctorId) {}

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(path = "/doctor/planned-visits/{visitId}")
    public void signUpForPlannedVisit(Principal principal, @PathVariable Long visitId) {}

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/doctor/planned-visits/{visitId}")
    public void cancelDoctorPlannedVisit(@PathVariable Long visitId) {}

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping(path = "/client/planned-visits/{visitId}")
    public void cancelClientPlannedVisit(@PathVariable Long visitId) {}

//    public void disablePlannedVisit() {}
}
