package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
public class PlannedVisitDto extends RepresentationModel<PlannedVisitDto> {

    private Long id;
    private LocalDateTime day;
    private boolean cancelled;
    private Long registeredDoctorId;
    private Long registeredClientId;
    private Long scheduledVisitId;

//    private RegisteredDoctor registeredDoctor;
//    private RegisteredClient registeredClient;
//    private ScheduledVisit scheduledVisit;

}
