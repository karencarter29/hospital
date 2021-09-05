package com.example.patient.Services;


import com.example.patient.DTO.PatientDTO;
import com.example.patient.Model.Patient;
import com.example.patient.Repositories.AppointmentRepository;
import com.example.patient.Repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {
    private ModelMapper modelMapper;
    private PatientRepository patientRepository;

    @Transactional
    public Patient addPatient(PatientDTO patient) {
        return patientRepository.save(convertToEntity(patient));
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> getPatient() {
        List<Patient> patientList = (List<Patient>)patientRepository.findAll();
        return patientList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @Transactional
    public Patient updatePatient(PatientDTO patient) {
        return patientRepository.save(convertToEntity(patient));
    }

    @Transactional
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
    private PatientDTO convertToDto(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }
    private Patient convertToEntity(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, Patient.class);
    }
}