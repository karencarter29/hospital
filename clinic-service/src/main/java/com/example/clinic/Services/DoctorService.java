package com.example.clinic.Services;


import com.example.clinic.DTO.DoctorDTO;
import com.example.clinic.Model.Doctor;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Repositories.DoctorRepository;
import com.example.clinic.Repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorService {
    private DoctorRepository doctorRepository;
    private RoomRepository roomRepository;
    private ModelMapper modelMapper;

    public Doctor saveDoctor(Doctor doctor, Speciality speciality) {
        //TODO:to get userId
        //TODO: to get speciality
        doctor.setSpeciality(speciality);
        return doctorRepository.save(doctor);
    }

    public List<DoctorDTO> getDoctors(){
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Doctor updateDoctor(Doctor doctor, Speciality specilaity) {
        //TODO:to get userId
        //TODO: to get speciality
        doctor.setSpeciality(specilaity);
        return doctorRepository.save(doctor);
    }
    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }

    private DoctorDTO convertToDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }
}

