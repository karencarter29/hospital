package com.example.clinic.Services;


import com.example.clinic.DTO.SpecialityDTO;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Repositories.DoctorRepository;
import com.example.clinic.Repositories.ProcedureRepository;
import com.example.clinic.Repositories.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SpecialityService {
    private ProcedureRepository procedureRepository;
    private SpecialityRepository specialityRepository;
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;

    public Speciality saveSpeciality(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    public List<SpecialityDTO> getSpecialities() {
        List<Speciality> specialityList = specialityRepository.findAll();
        return specialityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Speciality updateSpeciality(Speciality newSpeciality) {
        return specialityRepository.save(newSpeciality);
    }

    public void deleteSpeciality(int id) {
        specialityRepository.deleteById(id);
    }


    private SpecialityDTO convertToDto(Speciality speciality) {
        return modelMapper.map(speciality, SpecialityDTO.class);
    }
}
