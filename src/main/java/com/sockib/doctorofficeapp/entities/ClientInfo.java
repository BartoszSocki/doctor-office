package com.sockib.doctorofficeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clients_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registered_client_id", referencedColumnName = "pk_id")
    private RegisteredUser registeredUser;
}
