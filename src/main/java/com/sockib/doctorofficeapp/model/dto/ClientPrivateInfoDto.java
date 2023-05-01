package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
public class ClientPrivateInfoDto extends RepresentationModel<ClientPrivateInfoDto> {

    private Long id;
    private LocalDate dateOfBirth;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String gender;
    private String mobile;
    private Boolean verified;
}
