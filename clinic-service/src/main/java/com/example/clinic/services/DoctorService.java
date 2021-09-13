package com.example.clinic.services;

import com.example.clinic.dto.DoctorDTO;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Speciality;
import com.example.clinic.repositories.DoctorRepository;
import com.example.clinic.repositories.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorService {
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;
    private SpecialityRepository specialityRepository;

    @Transactional
    public Doctor saveDoctor(DoctorDTO doctor, UUID specialityId) {
        Speciality s = specialityRepository.findById(specialityId).orElse(null);
        doctor.setSpeciality(s);
        return doctorRepository.save(convertToEntity(doctor));
    }


    @Transactional(readOnly = true)
    public List<DoctorDTO> getDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public Doctor updateDoctor(DoctorDTO doctor, UUID specialityId) {
       return saveDoctor(doctor, specialityId);
    }

    @Transactional
    public void deleteDoctor(UUID id) {
        doctorRepository.deleteById(id);
    }

    private DoctorDTO convertToDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }


    private Doctor convertToEntity(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, Doctor.class);
    }
}

