package com.sockib.doctorofficeapp.controllers;

import com.sockib.doctorofficeapp.model.assemblers.ClientScheduledVisitModelAssembler;
import com.sockib.doctorofficeapp.model.assemblers.DoctorScheduledVisitModelAssembler;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitDto;
import com.sockib.doctorofficeapp.model.dto.ScheduledVisitFormDto;
import com.sockib.doctorofficeapp.services.ScheduledVisitsService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor

@CrossOrigin
@RestController
@RequestMapping(path = "/api")
public class ScheduledVisitsController {

    private ScheduledVisitsService scheduledVisitsService;
    private ClientScheduledVisitModelAssembler clientScheduledVisitModelAssembler;
    private DoctorScheduledVisitModelAssembler doctorScheduledVisitModelAssembler;

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/doctor/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisitDto> getVisit(@PathVariable Long visitId) {
        var scheduledVisit = scheduledVisitsService.getScheduledVisit(visitId);
        var scheduledVisitDto = clientScheduledVisitModelAssembler.toModel(scheduledVisit);

        return ResponseEntity.ok(scheduledVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping(path = "/doctor/scheduled-visits")
    public ResponseEntity<ScheduledVisitDto> addVisit(@RequestBody ScheduledVisitFormDto scheduledVisitFormDto) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var scheduledVisit = scheduledVisitsService.createScheduledVisit(scheduledVisitFormDto, authentication.getName());
        var scheduledVisitDto = clientScheduledVisitModelAssembler.toModel(scheduledVisit);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduledVisitDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(path = "/doctor/scheduled-visits")
    public ResponseEntity<CollectionModel<ScheduledVisitDto>> getVisits() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var scheduledVisits = scheduledVisitsService.getEnabledScheduledVisitsByDoctorUsername(authentication.getName());
        var scheduledVisitsDto = doctorScheduledVisitModelAssembler.toCollectionModel(scheduledVisits);

        return ResponseEntity.ok(scheduledVisitsDto);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping(path = "/doctor/scheduled-visits/{visitId}")
    public ResponseEntity<?> removeVisit(@PathVariable Long visitId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        scheduledVisitsService.disableScheduledVisit(visitId, authentication.getName());

        return ResponseEntity.noContent().build();
    }

//     TODO test it
    @PreAuthorize("hasRole('DOCTOR')")
    @PutMapping(path = "/doctor/scheduled-visits/{visitId}")
    public ResponseEntity<ScheduledVisitDto> updateVisit(@PathVariable Long visitId,
                                                         @RequestBody ScheduledVisitFormDto scheduledVisitFormDto) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var scheduledVisit = scheduledVisitsService.updateScheduledVisit(scheduledVisitFormDto, visitId, authentication.getName());
        var scheduledVisitDto = clientScheduledVisitModelAssembler.toModel(scheduledVisit);

        return ResponseEntity.ok(scheduledVisitDto);
    }

//    @GetMapping(path = "/{doctorId}/scheduled-visits")
//    public ResponseEntity<CollectionModel<ScheduledVisitDto>> getDoctorVisits(@PathVariable Long doctorId) {
//        var scheduledVisits = scheduledVisitsService.getEnabledScheduledVisitsForDoctorId(doctorId);
//        var scheduledVisitsDto = clientScheduledVisitModelAssembler.toCollectionModel(scheduledVisits);
//        scheduledVisitsDto.add(linkTo(methodOn(ScheduledVisitsController.class).getDoctorVisits(doctorId)).withSelfRel());
//
//        return ResponseEntity.ok(scheduledVisitsDto);
//    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping(path = "/client/doctor/{doctorId}/scheduled-visits")
    public ResponseEntity<CollectionModel<ScheduledVisitDto>> getDoctorVisitsForWeek(@PathVariable Long doctorId,
                                                                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> day) {
        var scheduledVisits = scheduledVisitsService.getEnabledScheduledVisitsForDoctorIdAndWeek(doctorId, day.orElse(LocalDate.now()));
        var scheduledVisitsDto = clientScheduledVisitModelAssembler.toCollectionModel(scheduledVisits);
        scheduledVisitsDto.add(linkTo(methodOn(ScheduledVisitsController.class).getDoctorVisitsForWeek(doctorId, day)).withSelfRel());

        return ResponseEntity.ok(scheduledVisitsDto);
    }

}
