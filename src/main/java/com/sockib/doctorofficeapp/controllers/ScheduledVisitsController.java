package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.ScheduledVisitDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.services.ScheduledVisitsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        var doctorId = scheduledVisit.getRegisteredDoctor().getId();

        scheduledVisitDto.add(linkTo(methodOn(ScheduledVisitsController.class).getVisit(visitId)).withSelfRel());
        scheduledVisitDto.add(linkTo(methodOn(ScheduledVisitsController.class).getAllVisits(doctorId)).withRel("getAllVisits"));

        return ResponseEntity.ok(scheduledVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping(path = "/scheduled-visits")
    public ResponseEntity<ScheduledVisitDto> addVisit(@RequestBody ScheduledVisitFormDto scheduledVisitFormDto, Principal principal) {
        var scheduledVisit = scheduledVisitsService.createScheduledVisit(scheduledVisitFormDto, principal);
        var scheduledVisitDto = modelMapper.map(scheduledVisit, ScheduledVisitDto.class);
        var scheduledVisitId = scheduledVisit.getId();
        var scheduledVisitDoctorId = scheduledVisit.getRegisteredDoctor().getId();

        scheduledVisitDto.add(linkTo(methodOn(ScheduledVisitsController.class)
                .getVisit(scheduledVisitId))
                .withSelfRel());

        scheduledVisitDto.add(linkTo(methodOn(ScheduledVisitsController.class)
                .getAllVisits(scheduledVisitDoctorId))
                .withRel(IanaLinkRelations.COLLECTION));

        return ResponseEntity.created(linkTo(methodOn(ScheduledVisitsController.class)
                .getVisit(scheduledVisitId)).toUri())
                .body(scheduledVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<?> removeVisit(@PathVariable Long visitId, Principal principal) {
        scheduledVisitsService.removeScheduledVisit(visitId, principal.getName());
        return ResponseEntity.noContent().build();
    }

//     TODO test it
    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping(path = "/scheduled-visits/{visitId}")
    public void updateVisit(@PathVariable Long visitId, @RequestBody ScheduledVisitFormDto scheduledVisitFormDto, Principal principal) {
        scheduledVisitsService.updateScheduledVisit(scheduledVisitFormDto, visitId, principal.getName());
    }

    @GetMapping(path = "/{doctorId}/scheduled-visits")
    public ResponseEntity<CollectionModel<ScheduledVisitDto>> getAllVisits(@PathVariable Long doctorId) {
        var scheduledVisits = scheduledVisitsService.getScheduledVisits(doctorId);
        var scheduledVisitsDto = scheduledVisits.stream()
                .map(v -> modelMapper.map(v, ScheduledVisitDto.class))
                .map(v -> v.add(linkTo(methodOn(ScheduledVisitsController.class).getVisit(v.getId())).withSelfRel()))
                .toList();

        CollectionModel<ScheduledVisitDto> scheduledVisitDtoCollectionModel = CollectionModel.of(scheduledVisitsDto);
        scheduledVisitDtoCollectionModel.add(linkTo(methodOn(ScheduledVisitsController.class).getAllVisits(doctorId)).withSelfRel());

        return ResponseEntity.ok(scheduledVisitDtoCollectionModel);
    }

}
