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
    private UserPrivateInfoDto userPrivateInfoDto;

}
