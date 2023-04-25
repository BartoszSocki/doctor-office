package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.services.ScheduledVisitsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping(path = "/api/doctor")
public class ScheduledVisitsController {

    private ScheduledVisitsService scheduledVisitsService;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisit> getVisit(@PathVariable Long visitId) {
        var scheduledVisit = scheduledVisitsService.getScheduledVisit(visitId);
        return ResponseEntity.ok(scheduledVisit);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping(path = "/scheduled-visits")
    public void addVisit(@RequestBody ScheduledVisitFormDto scheduledVisitFormDto, Principal principal) {
        scheduledVisitsService.createScheduledVisit(scheduledVisitFormDto, principal);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/scheduled-visits/{visitId}")
    public void removeVisit(@PathVariable Long visitId) {
        scheduledVisitsService.removeScheduledVisit(visitId);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping(path = "/scheduled-visits/{visitId}")
    public void updateVisit(@PathVariable Long visitId, @RequestBody ScheduledVisitFormDto scheduledVisitFormDto) {
        scheduledVisitsService.updateScheduledVisit(scheduledVisitFormDto, visitId);
    }

    @GetMapping(path = "/{doctorId}/scheduled-visits")
    public ResponseEntity<List<ScheduledVisit>> getAllVisits(@PathVariable Long doctorId) {
        var visits = scheduledVisitsService.getScheduledVisits(doctorId);
        return ResponseEntity.ok(visits);
    }

}
