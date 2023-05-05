package com.sockib.doctorofficeapp.controllers;

import com.nimbusds.jose.proc.SecurityContext;
import com.sockib.doctorofficeapp.entities.Note;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import com.sockib.doctorofficeapp.model.assemblers.NoteModelAssembler;
import com.sockib.doctorofficeapp.model.assemblers.ScheduledVisitModelAssembler;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.services.ScheduledVisitsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private ScheduledVisitModelAssembler scheduledVisitModelAssembler;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisitDto> getVisit(@PathVariable Long visitId) {
        var scheduledVisit = scheduledVisitsService.getScheduledVisit(visitId);
        var scheduledVisitDto = scheduledVisitModelAssembler.toModel(scheduledVisit);

        return ResponseEntity.ok(scheduledVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping(path = "/scheduled-visits")
    public ResponseEntity<ScheduledVisitDto> addVisit(@RequestBody ScheduledVisitFormDto scheduledVisitFormDto) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var scheduledVisit = scheduledVisitsService.createScheduledVisit(scheduledVisitFormDto, authentication.getName());
        var scheduledVisitDto = scheduledVisitModelAssembler.toModel(scheduledVisit);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduledVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/scheduled-visits/{visitId}")
    public void removeVisit(@PathVariable Long visitId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        scheduledVisitsService.disableScheduledVisit(visitId, authentication.getName());
    }

//     TODO test it
    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping(path = "/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisitDto> updateVisit(@PathVariable Long visitId,
                                                         @RequestBody ScheduledVisitFormDto scheduledVisitFormDto) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var scheduledVisit = scheduledVisitsService.updateScheduledVisit(scheduledVisitFormDto, visitId, authentication.getName());
        var scheduledVisitDto = scheduledVisitModelAssembler.toModel(scheduledVisit);

        return ResponseEntity.ok(scheduledVisitDto);
    }

    @GetMapping(path = "/{doctorId}/scheduled-visits")
    public ResponseEntity<List<ScheduledVisit>> getVisits(@PathVariable Long doctorId) {
        var scheduledVisits = scheduledVisitsService.getEnabledScheduledVisits(doctorId);
//        var scheduledVisitsDto = scheduledVisitModelAssembler.toCollectionModel(scheduledVisits);
//        scheduledVisitsDto.add(linkTo(methodOn(ScheduledVisitsController.class).getVisits(doctorId)).withSelfRel());

//        return ResponseEntity.ok(scheduledVisitsDto);
        return ResponseEntity.ok(scheduledVisits);
    }

}
