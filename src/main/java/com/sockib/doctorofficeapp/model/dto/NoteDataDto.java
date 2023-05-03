package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
public class NoteDataDto extends RepresentationModel<NoteDataDto> {

    private Long id;
    private String name;
    private String content;
    private LocalDate dateOfCreation;
    private LocalDate dateOfModification;

}
