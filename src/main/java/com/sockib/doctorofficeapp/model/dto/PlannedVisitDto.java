package com.sockib.doctorofficeapp.model.dto;

import com.sockib.doctorofficeapp.entities.RegisteredClient;
import com.sockib.doctorofficeapp.entities.RegisteredDoctor;
import com.sockib.doctorofficeapp.entities.ScheduledVisit;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlannedVisitDto {

    private Long id;
    private LocalDateTime day;
    private boolean cancelled;
    private Long registeredDoctorId;
    private Long registeredClientId;
    private Long scheduledVisitId;

//    private RegisteredDoctor registeredDoctor;
//    private RegisteredClient registeredClient;
//    private ScheduledVisit scheduledVisit;

}
