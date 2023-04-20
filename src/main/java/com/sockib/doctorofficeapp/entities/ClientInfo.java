package com.sockib.doctorofficeapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String username;
    private String name;
    private String surname;

    private String pesel;
    private String mobile;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registered_client_id", referencedColumnName = "id")
    private RegisteredClient registeredClient;
}
