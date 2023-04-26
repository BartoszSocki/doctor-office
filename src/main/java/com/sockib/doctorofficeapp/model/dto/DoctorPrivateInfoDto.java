package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;

@Data
public class DoctorPrivateInfoDto {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String pesel;
    private String specialization;
    private String pwz;

}
