package com.example.clinic.services;

import com.example.clinic.dto.HospitalDTO;
import com.example.clinic.model.Hospital;
import com.example.clinic.repositories.HospitalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HospitalService {
    private HospitalRepository hospitalRepository;
    private ModelMapper modelMapper;

    @Transactional
    public Hospital addHospital(HospitalDTO hospital) {
        return hospitalRepository.save(convertToEntity(hospital));
    }

    @Transactional(readOnly = true)
    public List<HospitalDTO> getHospitals() {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        return hospitalList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public Hospital updateHospital(HospitalDTO hospital) {
        return hospitalRepository.save(convertToEntity(hospital));
    }

    @Transactional
    public void deleteHospital(UUID id) {
        hospitalRepository.deleteById(id);
    }

    private HospitalDTO convertToDto(Hospital hospital) {
        return modelMapper.map(hospital, HospitalDTO.class);
    }
    private Hospital convertToEntity(HospitalDTO hospitalDTO) {
        return modelMapper.map(hospitalDTO, Hospital.class);
    }

}
