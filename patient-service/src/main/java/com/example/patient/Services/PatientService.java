package com.example.patient.Services;


import com.example.patient.DTO.PatientDTO;
import com.example.patient.Model.Appointment;
import com.example.patient.Model.Patient;
import com.example.patient.Repositories.AppointmentRepository;
import com.example.patient.Repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {
    private ModelMapper modelMapper;
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;

    public Patient addPatient(Patient patient) {
        //TODO: to get userId from Security
        //patient.setUserId(userId);
        return patientRepository.save(patient);
    }

    public List<PatientDTO> getPatient() {
        List<Patient> patientList = (List<Patient>)patientRepository.findAll();
        return patientList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public Patient updatePatient(Patient patient) {
        //TODO: to get userId from Security
       // patient.setUserId(userId);
        return patientRepository.save(patient);
    }

    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
    private PatientDTO convertToDto(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }
}