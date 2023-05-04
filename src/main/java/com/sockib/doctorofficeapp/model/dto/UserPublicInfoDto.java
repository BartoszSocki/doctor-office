package com.sockib.doctorofficeapp.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserPublicInfoDto extends RepresentationModel<UserPublicInfoDto> {

    private Long id;
    private String name;
    private String surname;
    private String gender;

}
