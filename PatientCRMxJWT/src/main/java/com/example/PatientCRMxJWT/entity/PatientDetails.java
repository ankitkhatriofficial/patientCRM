package com.example.PatientCRMxJWT.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Ankit Kumar
 */
@Entity
@Table(name = "details")
@Data
public class PatientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String name;
    private String email;
    private String phoneNo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address address;


}
