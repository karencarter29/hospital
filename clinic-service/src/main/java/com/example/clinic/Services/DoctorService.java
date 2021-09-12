package com.example.clinic.Services;

import com.example.clinic.DTO.DoctorDTO;
import com.example.clinic.Model.Doctor;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Repositories.DoctorRepository;
import com.example.clinic.Repositories.SpecialityRepository;
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

