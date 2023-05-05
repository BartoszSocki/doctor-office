package com.sockib.doctorofficeapp.model.dto;

import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = false)
@Data
public class ScheduledVisitDto extends RepresentationModel<ScheduledVisitDto> {

    private Long id;
    private DayOfTheWeek dayOfTheWeek;
    private LocalTime visitBegTime;
    private LocalTime visitEndTime;
    private Integer price;
    private String type;
    private Long registeredDoctorId;

    private AddressDto address;

}
