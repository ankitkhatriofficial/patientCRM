package com.example.PatientCRMxJWT.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author Ankit Kumar
 */
@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer zipCode;
    private String line1;
    private String line2;
    private String city;

}
