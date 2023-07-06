package com.example.PatientCRMxJWT.controller;

import com.example.PatientCRMxJWT.dto.JwtRequest;
import com.example.PatientCRMxJWT.dto.PatientDto;
import com.example.PatientCRMxJWT.entity.PatientDetails;
import com.example.PatientCRMxJWT.security.JwtUtil;
import com.example.PatientCRMxJWT.service.PatientService;
import com.example.PatientCRMxJWT.service.UserDetailsSeviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ankit Kumar
 */
@RestController
public class PatientController {
    @Autowired private PatientService patientService;
    @Autowired private UserDetailsSeviceImpl userDetailsService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/generate/token")
    public ResponseEntity<?> generateJwtToken(@RequestBody JwtRequest request){
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getUsername()));
        }catch(Exception e){
            throw new RuntimeException("Bad credentials..!");
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtUtil.generateJwtToken(userDetails);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /*Add new patient*/
    @PostMapping("/add-new-patient")
    public ResponseEntity<PatientDetails> addNewPatient(@Valid @RequestBody PatientDto input, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getAllErrors().toString());
        }
        return new ResponseEntity<>(patientService.addNewPatient(input), HttpStatus.CREATED);
    }

    /*get patient details by id*/
    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    /*get all patient details*/
    @GetMapping("/all-patients")
    public ResponseEntity<List<PatientDetails>> getAll() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    /*delete  patient details by id*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        patientService.DeleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    /*delete all patient details*/
    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAll() {
        patientService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*update patient name */
    @PatchMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id, @RequestBody PatientDto input) {
        patientService.update(id, input);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
