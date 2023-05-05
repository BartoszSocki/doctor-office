package com.sockib.doctorofficeapp.entities;

import com.sockib.doctorofficeapp.entities.embeded.Address;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalTime;

@Data

@Entity
@Table(name = "scheduled_visits")
public class ScheduledVisit {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_the_week")
    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfTheWeek;

    @Column(name = "beg_time")
    private LocalTime visitBegTime;

    @Column(name = "end_time")
    private LocalTime visitEndTime;

    private Integer price;

    @Embedded
    private Address address;

    private String type;
    private Boolean disabled = false;

    @ManyToOne
    @JoinColumn(name = "fk_registered_doctor_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredDoctor;
}