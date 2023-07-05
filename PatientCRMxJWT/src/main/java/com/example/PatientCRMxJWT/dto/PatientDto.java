package com.example.PatientCRMxJWT.dto;

import com.example.PatientCRMxJWT.entity.Address;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author Ankit Kumar
 */
@Data
public class PatientDto {

    @NotEmpty
    private String name;
    @NotEmpty
    @Email(message = "email must be of correct format")
    private String email;
    @Pattern(regexp = "[\\d]{10}", message = "Should contain 10 digits")
    private String phoneNo;
    Address address;

}
