package com.example.clinic.Services;


import com.example.clinic.DTO.HospitalDTO;
import com.example.clinic.DTO.SpecialityDTO;
import com.example.clinic.Model.Hospital;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Repositories.DoctorRepository;
import com.example.clinic.Repositories.ProcedureRepository;
import com.example.clinic.Repositories.SpecialityRepository;
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
