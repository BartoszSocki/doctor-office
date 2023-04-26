package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class ClientPrivateInfoDto extends RepresentationModel<ClientPrivateInfoDto> {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String pesel;
    private String mobile;

}
