package com.sockib.doctorofficeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "planned_visits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlannedVisit {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_day")
    private LocalDateTime day;
    private boolean cancelled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_registered_doctor_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredDoctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_registered_client_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredClient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_scheduled_visit_id", referencedColumnName = "pk_id")
    private ScheduledVisit scheduledVisit;
}