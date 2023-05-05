package com.sockib.doctorofficeapp.entities.embeded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data

@Embeddable
public class Address {

    private String country;
    private String city;
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

}
