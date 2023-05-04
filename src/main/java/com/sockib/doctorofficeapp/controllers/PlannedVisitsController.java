package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.entities.PlannedVisit;
import com.sockib.doctorofficeapp.model.assemblers.ClientPlannedVisitModelAssembler;
import com.sockib.doctorofficeapp.model.assemblers.DoctorPlannedVisitModelAssembler;
import com.sockib.doctorofficeapp.model.dto.PlannedVisitDto;
import com.sockib.doctorofficeapp.services.PlannedVisitsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@AllArgsConstructor

@RestController
@RequestMapping(path = "/api")
public class PlannedVisitsController {

    private PlannedVisitsService plannedVisitsService;
//    private ModelMapper modelMapper;
    private PagedResourcesAssembler<PlannedVisit> pagedResourcesAssembler;
    private ClientPlannedVisitModelAssembler clientPlannedVisitModelAssembler;
    private DoctorPlannedVisitModelAssembler doctorPlannedVisitModelAssembler;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/client/planned-visits")
    public ResponseEntity<PagedModel<PlannedVisitDto>> getClientPlannedVisits(Pageable pageable) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var plannedVisits = plannedVisitsService.getClientPlannedVisits(authentication.getName(), pageable);
        var collectionModel = pagedResourcesAssembler.toModel(plannedVisits, clientPlannedVisitModelAssembler);

        return ResponseEntity.ok(collectionModel);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/client/planned-visits/{visitId}")
    public ResponseEntity<PlannedVisitDto> getClientPlannedVisit(@PathVariable Long visitId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var plannedVisit = plannedVisitsService.getClientPlannedVisit(authentication.getName(), visitId);
        var model = clientPlannedVisitModelAssembler.toModel(plannedVisit);

        return ResponseEntity.ok(model);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/doctor/planned-visits")
    public ResponseEntity<PagedModel<PlannedVisitDto>> getDoctorPlannedVisits(Pageable pageable) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var plannedVisits = plannedVisitsService.getDoctorPlannedVisits(authentication.getName(), pageable);
        var collectionModel = pagedResourcesAssembler.toModel(plannedVisits, doctorPlannedVisitModelAssembler);

        return ResponseEntity.ok(collectionModel);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/doctor/planned-visits/{visitId}")
    public ResponseEntity<PlannedVisitDto> getDoctorPlannedVisit(@PathVariable Long visitId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var plannedVisit = plannedVisitsService.getDoctorPlannedVisit(authentication.getName(), visitId);
        var model = clientPlannedVisitModelAssembler.toModel(plannedVisit);

        return ResponseEntity.ok(model);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping(path = "/client/planned-visits")
    public ResponseEntity<PlannedVisitDto> requestPlannedVisit(@RequestParam(name = "id") Long scheduledVisitId,
                                                               @RequestParam(name = "date") LocalDate date) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var plannedVisit = plannedVisitsService.requestPlannedVisit(authentication.getName(), scheduledVisitId, date);
        var plannedVisitDto = clientPlannedVisitModelAssembler.toModel(plannedVisit);

        return ResponseEntity.status(HttpStatus.CREATED).body(plannedVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/doctor/planned-visits/{visitId}")
    public void cancelDoctorPlannedVisit(@PathVariable Long visitId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        plannedVisitsService.cancelDoctorPlannedVisit(visitId, authentication.getName());
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping(path = "/client/planned-visits/{visitId}")
    public void cancelClientPlannedVisit(@PathVariable Long visitId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        plannedVisitsService.cancelClientPlannedVisit(visitId, authentication.getName());
    }

}
