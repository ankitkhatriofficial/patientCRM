package com.example.PatientCRMxJWT.service;

import com.example.PatientCRMxJWT.dto.PatientDto;
import com.example.PatientCRMxJWT.entity.PatientDetails;

import java.util.List;
import java.util.Optional;

/**
 * @author Ankit Kumar
 */
public interface PatientService {
    PatientDetails addNewPatient(PatientDto input);

    List<PatientDetails> getAllPatients();

    Optional getPatientById(Integer id);

    void DeleteById(Integer id);

    void update(Integer id, PatientDto input);

    void deleteAll();
}
