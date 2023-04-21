package com.sockib.doctorofficeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sockib.doctorofficeapp.enums.DayOfTheWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "scheduled_visits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledVisit {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_the_week")
    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfTheWeek;

    @Column(name = "beg")
    private LocalTime visitBegTime;

    @Column(name = "end")
    private LocalTime visitEndTime;

    private Integer price;
    private String localization;
    private String type;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_registered_doctor_id", referencedColumnName = "id")
    private RegisteredDoctor registeredDoctor;
}