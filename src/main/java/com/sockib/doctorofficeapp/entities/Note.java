package com.sockib.doctorofficeapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String content;

    @Column(name = "date_of_creation")
    @CreationTimestamp
    private LocalDate dateOfCreation;

    @Column(name = "date_of_modification")
    @UpdateTimestamp
    private LocalDate dateOfModification;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_registered_doctor_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredDoctor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_planned_visit_id", referencedColumnName = "pk_id")
    private PlannedVisit plannedVisit;

}
