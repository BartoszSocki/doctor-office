package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;

@Data
public class ClientPrivateInfoDto {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String pesel;
    private String mobile;

}
