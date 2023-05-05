package com.sockib.doctorofficeapp.entities;

import com.sockib.doctorofficeapp.entities.embeded.VisitStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data

@Entity
@Table(name = "planned_visits")
public class PlannedVisit {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_day")
    private LocalDateTime day;

    @Embedded
    private VisitStatus visitStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_registered_doctor_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredDoctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_registered_client_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_scheduled_visit_id", referencedColumnName = "pk_id")
    private ScheduledVisit scheduledVisit;
}