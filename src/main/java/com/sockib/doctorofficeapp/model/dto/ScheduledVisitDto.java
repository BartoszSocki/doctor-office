package com.sockib.doctorofficeapp.model.dto;

import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduledVisitDto {

    private Long id;
    private DayOfTheWeek dayOfTheWeek;
    private LocalTime visitBegTime;
    private LocalTime visitEndTime;
    private Integer price;
    private String localization;
    private String type;
    private Long registeredDoctorId;

}
