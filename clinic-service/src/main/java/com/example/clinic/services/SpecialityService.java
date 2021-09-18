package com.example.clinic.services;


import com.example.clinic.dto.SpecialityDTO;
import com.example.clinic.model.Speciality;
import com.example.clinic.repositories.DoctorRepository;
import com.example.clinic.repositories.ProcedureRepository;
import com.example.clinic.repositories.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SpecialityService {
    private ProcedureRepository procedureRepository;
    private SpecialityRepository specialityRepository;
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;

    @Transactional
    public Speciality saveSpeciality(SpecialityDTO speciality) {
        return specialityRepository.save(convertToEntity(speciality));
    }

    @Transactional(readOnly = true)
    public List<SpecialityDTO> getSpecialities() {
        List<Speciality> specialityList = specialityRepository.findAll();
        return specialityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public Speciality updateSpeciality(SpecialityDTO newSpeciality) {
        return saveSpeciality(newSpeciality);
    }

    @Transactional
    public void deleteSpeciality(UUID id) {
        specialityRepository.deleteById(id);
    }


    private SpecialityDTO convertToDto(Speciality speciality) {
        return modelMapper.map(speciality, SpecialityDTO.class);
    }

    private Speciality convertToEntity(SpecialityDTO specialityDTO) {
        return modelMapper.map(specialityDTO, Speciality.class);
    }
}
