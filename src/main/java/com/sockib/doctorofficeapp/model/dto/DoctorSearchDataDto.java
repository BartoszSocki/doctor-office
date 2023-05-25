package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@EqualsAndHashCode(callSuper = false)
@Data

@Relation(collectionRelation = "doctors", itemRelation = "doctor")
public class DoctorSearchDataDto extends RepresentationModel<DoctorSearchDataDto> {

    private Long id;
    private String name;
    private String surname;
    private String specialization;

}
