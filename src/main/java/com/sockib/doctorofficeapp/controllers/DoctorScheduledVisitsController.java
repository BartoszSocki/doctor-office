package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.RegisteredDoctor;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.repositories.DoctorCredentialsRepository;
import com.sockib.doctorofficeapp.repositories.ScheduledVisitRepository;
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

    private ScheduledVisitRepository scheduledVisitRepository;
    private DoctorCredentialsRepository doctorCredentialsRepository;

    @GetMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisit> getVisit(@PathVariable Long visitId) {
        var visit = scheduledVisitRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("TODO"));

        return ResponseEntity.ok(visit);
    }

    @PostMapping(path = "/scheduled-visits")
    public void addVisit(@RequestBody ScheduledVisitFormDto scheduledVisitFormDto, Principal principal) {
        var scheduledVisit = new ScheduledVisit();

        scheduledVisit.setVisitBegTime(scheduledVisitFormDto.getVisitBegTime());
        scheduledVisit.setVisitEndTime(scheduledVisitFormDto.getVisitEndTime());
        scheduledVisit.setType(scheduledVisitFormDto.getType());
        scheduledVisit.setPrice(scheduledVisitFormDto.getPrice());

        // TODO needs validation
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));
        scheduledVisit.setLocalization(scheduledVisitFormDto.getLocalization());

        var registeredDoctor = doctorCredentialsRepository.findRegisteredDoctorByUsername(principal.getName())
                        .orElseThrow(() -> new UsernameNotFoundException(principal.getName()));

        scheduledVisit.setRegisteredDoctor(registeredDoctor);
        scheduledVisitRepository.save(scheduledVisit);
    }

    @DeleteMapping(path = "/scheduled-visits/{visitId}")
    public void removeVisit(@PathVariable Long visitId) {
        scheduledVisitRepository.deleteById(visitId);
    }

    @PutMapping(path = "/scheduled-visits/{visitId}")
    public void updateVisit(@PathVariable Long visitId, @RequestBody ScheduledVisitFormDto scheduledVisitFormDto) {
        var scheduledVisit = scheduledVisitRepository.findById(visitId).
                orElseThrow(() -> new RuntimeException("TODO"));

        scheduledVisit.setVisitBegTime(scheduledVisitFormDto.getVisitBegTime());
        scheduledVisit.setVisitEndTime(scheduledVisitFormDto.getVisitEndTime());
        scheduledVisit.setType(scheduledVisitFormDto.getType());
        scheduledVisit.setPrice(scheduledVisitFormDto.getPrice());

        // TODO needs validation
        scheduledVisit.setDayOfTheWeek(DayOfTheWeek.valueOf(scheduledVisitFormDto.getDayOfTheWeek()));
        scheduledVisit.setLocalization(scheduledVisitFormDto.getLocalization());

        scheduledVisitRepository.save(scheduledVisit);

    }

    // TODO change endpoint
    @GetMapping(path = "/{doctorId}/scheduled-visits")
    public ResponseEntity<List<ScheduledVisit>> etAllVisits(@PathVariable Long doctorId) {
        var visits = scheduledVisitRepository.findScheduledVisitsByRegisteredDoctorId(doctorId);

        return ResponseEntity.ok(visits);
    }


}
