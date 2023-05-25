package com.sockib.doctorofficeapp.model.dto;

import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledVisitFormDto {

    private String dayOfTheWeek;
    private LocalTime visitBegTime;
    private LocalTime visitEndTime;
    private Integer price;
    private String localization;
    private String type;

    private AddressDto address;
//    private String country;
//    private String city;
//    private String street;
//    private String zipCode;

}
