package com.example.springauthapi.entities;

import com.example.springauthapi.authorities.UserAuthorities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

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
    private UserAuthorities authority;

//    @ManyToMany
//    private Collection<User> users;

}
