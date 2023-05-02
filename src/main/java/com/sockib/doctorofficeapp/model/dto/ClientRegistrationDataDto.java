package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRegistrationDataDto {

    private String username;
    private String name;
    private String surname;
    private String password;
    private String matchingPassword;

    private String gender;
    private String mobile;

    private LocalDate dateOfBirth;

}
