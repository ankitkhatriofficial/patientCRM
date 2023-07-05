package com.example.PatientCRMxJWT.service.impl;

import com.example.PatientCRMxJWT.dto.PatientDto;
import com.example.PatientCRMxJWT.entity.PatientDetails;
import com.example.PatientCRMxJWT.repository.PatientRepo;
import com.example.PatientCRMxJWT.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author Ankit Kumar
 */
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepo repo;

    @Override
    public PatientDetails addNewPatient(PatientDto input) {
        PatientDetails newPatient = new PatientDetails();
        newPatient.setName(input.getName());
        newPatient.setEmail(input.getEmail());
        newPatient.setPhoneNo(input.getPhoneNo());
        newPatient.setAddress(input.getAddress());

        return repo.saveAndFlush(newPatient);
    }

    @Override
    public List<PatientDetails> getAllPatients() {
        return repo.findAll();
    }

    @Override
    public Optional<PatientDetails> getPatientById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void DeleteById(Integer id) {
        Optional patient = repo.findById(id);
        if (patient.isPresent()) {
            repo.deleteById(id);
        }
    }

    @Override
    public void update(Integer id, PatientDto input) {
        Optional<PatientDetails> patient = repo.findById(id);
        if (patient.isPresent()) {
            patient.get().setName(input.getName());
            repo.saveAndFlush(patient.get());
        }

    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

}
