package com.sockib.doctorofficeapp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class DoctorPublicInfoDto extends RepresentationModel<DoctorPublicInfoDto> {

    private String specialization;
    private Long id;

    @JsonProperty("publicInfo")
    private UserPublicInfoDto userPublicInfoDto;

}
