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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String username;
    private String name;
    private String surname;
    private String specialization;
    private String mobile;

    private String pesel;
    private String pwz;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registered_doctor_id", referencedColumnName = "id")
    private RegisteredDoctor registeredDoctor;

}
