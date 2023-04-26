package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;

@Data
public class DoctorPublicInfoDto {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String specialization;

}
