package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data

@Relation(collectionRelation = "notes", itemRelation = "note")
public class NoteDataDto extends RepresentationModel<NoteDataDto> {

    private Long id;
    private String name;
    private String content;
    private LocalDate dateOfCreation;
    private LocalDate dateOfModification;

}
