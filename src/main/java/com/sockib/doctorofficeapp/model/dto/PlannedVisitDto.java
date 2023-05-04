package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data

@Relation(collectionRelation = "planned_visits", itemRelation = "planned_visit")
public class PlannedVisitDto extends RepresentationModel<PlannedVisitDto> {

    private Long id;
    private LocalDateTime day;
    private boolean cancelled;
    private Long registeredDoctorId;
    private Long registeredClientId;
    private Long scheduledVisitId;

}
