package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class ClientPublicInfoDto extends RepresentationModel<ClientPublicInfoDto> {

    private Long id;
    private String name;
    private String surname;

}
