package com.example.PatientCRMxJWT.repository;

import com.example.PatientCRMxJWT.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ankit Kumar
 */
public interface PatientRepo extends JpaRepository<PatientDetails,Integer> {
}
