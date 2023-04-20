package com.sockib.doctorofficeapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

// TODO validate fields

@Getter
@Setter
public class DoctorInfoDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String specialization;

    @Null
    private String pesel;

    @Null
    private String pwz;

}
