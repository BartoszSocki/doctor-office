package com.sockib.doctorofficeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctor_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPrivateInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // unidirectional
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registered_user_id", referencedColumnName = "id")
    @JsonIgnore
    private RegisteredUser registeredUser;

    private String name;
    private String surname;
    private String specialization;

    private String pesel;
    private String pwz;

}
