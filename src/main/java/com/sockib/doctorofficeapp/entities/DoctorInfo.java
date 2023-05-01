package com.sockib.doctorofficeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfo {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registered_doctor_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredDoctor;

}
