package com.example.clinic.Services;

import com.example.clinic.DTO.HospitalDTO;
import com.example.clinic.Model.Hospital;
import com.example.clinic.Repositories.HospitalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HospitalService {
    private HospitalRepository hospitalRepository;
    private ModelMapper modelMapper;

    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public List<HospitalDTO> getHospitals() {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        return hospitalList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Hospital updateHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public void deleteHospital(int id) {
        hospitalRepository.deleteById(id);
    }

    private HospitalDTO convertToDto(Hospital hospital) {
        return modelMapper.map(hospital, HospitalDTO.class);
    }

}
