package com.sockib.doctorofficeapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "doctors_info")
public class DoctorInfo {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registered_doctor_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredUser;

}
