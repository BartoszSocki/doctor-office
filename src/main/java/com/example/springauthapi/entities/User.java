package com.example.springauthapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_auth_id", referencedColumnName = "id")
    private UserAuth userAuth;

}
