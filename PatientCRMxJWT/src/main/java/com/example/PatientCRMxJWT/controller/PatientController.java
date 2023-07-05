package com.example.PatientCRMxJWT.controller;

import com.example.PatientCRMxJWT.dto.PatientDto;
import com.example.PatientCRMxJWT.entity.PatientDetails;
import com.example.PatientCRMxJWT.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Ankit Kumar
 */
@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;

    /*Add new patient*/
    @PostMapping("/add-new-patient")
    PatientDetails addNewPatient(@Valid @RequestBody PatientDto input, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getAllErrors().toString());
        }
        return patientService.addNewPatient(input);
    }

    /*get patient details by id*/
    @GetMapping("/patient/{id}")
    Optional getById(@PathVariable("id") Integer id) {
        return patientService.getPatientById(id);
    }

    /*get all patient details*/
    @GetMapping("/all-patients")
    List<PatientDetails> getAll() {
        return patientService.getAllPatients();
    }

    /*delete  patient details by id*/
    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable("id") Integer id) {
        patientService.DeleteById(id);
    }

    /*delete all patient details*/
    @DeleteMapping("/delete-all")
    void deleteAll() {
        patientService.deleteAll();
    }

    /*update patient name */
    @PatchMapping("update/{id}")
    void updateById(@PathVariable("id") Integer id, @RequestBody PatientDto input) {
        patientService.update(id, input);
    }

}
