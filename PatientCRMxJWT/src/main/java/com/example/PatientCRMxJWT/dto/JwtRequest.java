package com.example.PatientCRMxJWT.dto;

import lombok.Data;

/**
 * @author Ankit Khatri
 */

@Data
public class JwtRequest {

    private String username;
    private String password;

}

