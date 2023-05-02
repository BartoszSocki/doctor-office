package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;

@Data
public class DoctorRegistrationDataDto {

    private String username;
    private String name;
    private String surname;
    private String password;
    private String matchingPassword;

    private String gender;
    private String mobile;

    private String specialization;

}
