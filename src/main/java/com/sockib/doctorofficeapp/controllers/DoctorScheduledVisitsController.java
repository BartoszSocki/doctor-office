package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.RegisteredDoctor;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.repositories.DoctorCredentialsRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitRepository;
import com.sockib.doctorofficeapp.services.DoctorScheduledVisitsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping(path = "/api/doctor")
public class DoctorScheduledVisitsController {

    private DoctorScheduledVisitsService doctorScheduledVisitsService;

    @GetMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisit> getVisit(@PathVariable Long visitId) {
        var scheduledVisit = doctorScheduledVisitsService.getScheduledVisits(visitId);
        return ResponseEntity.ok(scheduledVisit);
    }

    @PostMapping(path = "/scheduled-visits")
    public void addVisit(@RequestBody ScheduledVisitFormDto scheduledVisitFormDto, Principal principal) {
        doctorScheduledVisitsService.createScheduledVisit(scheduledVisitFormDto, principal);
    }

    @DeleteMapping(path = "/scheduled-visits/{visitId}")
    public void removeVisit(@PathVariable Long visitId) {
        doctorScheduledVisitsService.removeScheduledVisit(visitId);
    }

    @PutMapping(path = "/scheduled-visits/{visitId}")
    public void updateVisit(@PathVariable Long visitId, @RequestBody ScheduledVisitFormDto scheduledVisitFormDto) {
        doctorScheduledVisitsService.updateScheduledVisit(scheduledVisitFormDto, visitId);
    }

    // TODO change endpoint
//    @GetMapping(path = "/{doctorId}/scheduled-visits")
//    public ResponseEntity<List<ScheduledVisit>> getAllVisits(@PathVariable Long doctorId) {
//        var visits = scheduledVisitRepository.findScheduledVisitsByRegisteredDoctorId(doctorId);
//
//        return ResponseEntity.ok(visits);
//    }

}
