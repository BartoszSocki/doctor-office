package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.dto.ScheduledVisitDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.services.ScheduledVisitsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
        var scheduledVisitDto = modelMapper.map(scheduledVisit, ScheduledVisitDto.class)
                .add(linkTo(methodOn(ScheduledVisitsController.class).getVisit(visitId)).withSelfRel());

        return ResponseEntity.ok(scheduledVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping(path = "/scheduled-visits")
    public ResponseEntity<ScheduledVisitDto> addVisit(@RequestBody ScheduledVisitFormDto scheduledVisitFormDto,
                                                      Principal principal) {
        var scheduledVisit = scheduledVisitsService.createScheduledVisit(scheduledVisitFormDto, principal.getName());
        var scheduledVisitDto = modelMapper.map(scheduledVisit, ScheduledVisitDto.class)
                .add(linkTo(methodOn(ScheduledVisitsController.class)
                .getVisit(scheduledVisit.getId()))
                .withSelfRel());

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduledVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<?> removeVisit(@PathVariable Long visitId, Principal principal) {
        scheduledVisitsService.disableScheduledVisit(visitId, principal.getName());
        return ResponseEntity.noContent().build();
    }

//     TODO test it
    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisitDto> updateVisit(@PathVariable Long visitId,
                                                         @RequestBody ScheduledVisitFormDto scheduledVisitFormDto,
                                                         Principal principal) {
        var scheduledVisit = scheduledVisitsService.updateScheduledVisit(scheduledVisitFormDto, visitId, principal.getName());
        var scheduledVisitDto = modelMapper.map(scheduledVisit, ScheduledVisitDto.class)
                .add(linkTo(methodOn(ScheduledVisitsController.class).getVisit(visitId)).withSelfRel());

        return ResponseEntity.ok(scheduledVisitDto);
    }

    @GetMapping(path = "/{doctorId}/scheduled-visits")
    public ResponseEntity<List<ScheduledVisitDto>> getVisits(@PathVariable Long doctorId) {
        var scheduledVisits = scheduledVisitsService.getEnabledScheduledVisits(doctorId);
        var scheduledVisitsDto = scheduledVisits.stream()
                .map(v -> modelMapper.map(v, ScheduledVisitDto.class))
                .map(v -> v.add(linkTo(methodOn(ScheduledVisitsController.class).getVisit(v.getId())).withSelfRel()))
                .toList();

        return ResponseEntity.ok(scheduledVisitsDto);
    }

}
