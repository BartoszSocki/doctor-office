package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.PlannedVisitDto;
import com.sockib.doctorofficeapp.services.PlannedVisitsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.core.WebHandler.linkTo;

@AllArgsConstructor

@RestController
@RequestMapping(path = "/api")
public class PlannedVisitsController {

    private PlannedVisitsService plannedVisitsService;
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/client/planned-visits")
    public ResponseEntity<Page<PlannedVisitDto>> getClientPlannedVisits(Pageable pageable, Principal principal) {
        var plannedVisits = plannedVisitsService.getClientPlannedVisits(principal.getName(), pageable)
                .map(v -> modelMapper.map(v, PlannedVisitDto.class));
//                .map(v -> v.add(linkTo(methodOn(PlannedVisitsController.class))));

        return ResponseEntity.ok(plannedVisits);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/doctor/planned-visits")
    public ResponseEntity<Page<PlannedVisitDto>> getDoctorPlannedVisits(Pageable pageable, Principal principal) {
        var plannedVisits = plannedVisitsService.getDoctorPlannedVisits(principal.getName(), pageable)
                .map(v -> modelMapper.map(v, PlannedVisitDto.class));

        return ResponseEntity.ok(plannedVisits);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(path = "/client/planned-visits")
    public ResponseEntity<PlannedVisitDto> requestPlannedVisit(Principal principal,
                                                               @RequestParam(name = "id") Long scheduledVisitId,
                                                               @RequestParam(name = "date") LocalDate date) {
        var plannedVisit = plannedVisitsService.requestPlannedVisit(principal.getName(), scheduledVisitId, date);
        var plannedVisitDto = modelMapper.map(plannedVisit, PlannedVisitDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(plannedVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/doctor/planned-visits/{visitId}")
    public void cancelDoctorPlannedVisit(@PathVariable Long visitId, Principal principal) {
        plannedVisitsService.cancelDoctorPlannedVisit(visitId, principal.getName());
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping(path = "/client/planned-visits/{visitId}")
    public void cancelClientPlannedVisit(@PathVariable Long visitId, Principal principal) {
        plannedVisitsService.cancelClientPlannedVisit(visitId, principal.getName());
    }

}
