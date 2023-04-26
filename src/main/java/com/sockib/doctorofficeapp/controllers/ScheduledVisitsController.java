package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.model.dto.PlannedVisitDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.services.ScheduledVisitsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@RestController
@RequestMapping(path = "/api/doctor")
public class ScheduledVisitsController {

    private ScheduledVisitsService scheduledVisitsService;
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisitDto> getVisit(@PathVariable Long visitId) {
        var scheduledVisit = scheduledVisitsService.getScheduledVisit(visitId);
        var scheduledVisitDto = modelMapper.map(scheduledVisit, ScheduledVisitDto.class);

        return ResponseEntity.ok(scheduledVisitDto);
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
    public ResponseEntity<List<ScheduledVisitDto>> getAllVisits(@PathVariable Long doctorId) {
        var scheduledVisits = scheduledVisitsService.getScheduledVisits(doctorId);
        var scheduledVisitsDto = scheduledVisits.stream()
                .map(v -> modelMapper.map(v, ScheduledVisitDto.class))
                .toList();
        return ResponseEntity.ok(scheduledVisitsDto);
    }

}
