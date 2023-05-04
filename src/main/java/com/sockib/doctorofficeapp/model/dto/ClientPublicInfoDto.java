package com.sockib.doctorofficeapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
public class ClientPublicInfoDto extends RepresentationModel<ClientPublicInfoDto> {

    private Long id;
    private LocalDate dateOfBirth;

    @JsonProperty("publicInfo")
    private UserPublicInfoDto userPublicInfoDto;

}
