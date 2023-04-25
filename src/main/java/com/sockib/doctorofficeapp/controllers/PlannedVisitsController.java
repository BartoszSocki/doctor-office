package com.sockib.doctorofficeapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class PlannedVisitsController {

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/client/planned-visits")
    public void getClientsPlannedVisits() {}

    @GetMapping(path = "/doctor/{doctorId}/planned-visits")
    public void getDoctorPlannedVisits(@PathVariable Long doctorId) {}

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/doctor/planned-visits/{visitId}")
    public void cancelDoctorPlannedVisit(@PathVariable Long visitId) {}

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping(path = "/client/planned-visits/{visitId}")
    public void cancelClientPlannedVisit(@PathVariable Long visitId) {}

//    public void disablePlannedVisit() {}
}
