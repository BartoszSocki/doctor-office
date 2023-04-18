package com.example.springauthapi.entities;

import com.example.springauthapi.roles.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated
    private Roles authority;

//    @ManyToMany
//    private Collection<User> users;

}
