package com.sockib.doctorofficeapp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data

@Entity
@Table(name = "clients_info")
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
