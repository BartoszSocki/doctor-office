package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = false)
@Data

@Relation(collectionRelation = "plannedVisits", itemRelation = "plannedVisit")
public class PlannedVisitDto extends RepresentationModel<PlannedVisitDto> {

    private Long id;
    private LocalDateTime day;
    private LocalTime begTime;
    private LocalTime endTime;
    private Boolean cancelled;

    private Long registeredDoctorId;
    private Long registeredClientId;

    private String clientName;
    private String clientSurname;

    private String doctorName;
    private String doctorSurname;

    private String city;
    private String street;
}
