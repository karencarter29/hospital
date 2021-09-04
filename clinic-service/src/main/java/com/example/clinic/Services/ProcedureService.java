package com.example.clinic.Services;


import com.example.clinic.DTO.ProcedureDTO;

import com.example.clinic.Model.Procedure;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Repositories.ProcedureRepository;
import com.example.clinic.Repositories.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProcedureService {
    private ProcedureRepository procedureRepository;
    private SpecialityRepository specialityRepository;
    private ModelMapper modelMapper;

    @Transactional
    public Procedure addProcedure(Procedure procedure, Speciality speciality) {
        //TODO: to get specialityId
        procedure.setSpeciality(speciality);
        return procedureRepository.save(procedure);
    }

    @Transactional(readOnly = true)
    public List<ProcedureDTO> getProcedures() {
        List<Procedure> procedures = procedureRepository.findAll();
        return procedures.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> getProcedureNames() {
        List<Procedure> procedures = procedureRepository.findAll();
        List<String> names = new LinkedList<>();
        for(Procedure p: procedures) {
            names.add(p.getProcedureName());
        }
        return names;
    }

    @Transactional
    public Procedure updateProcedure(Procedure newProcedure, Speciality speciality) {
        //TODO: to get specialityId
        newProcedure.setSpeciality(speciality);
        return procedureRepository.save(newProcedure);
    }

    @Transactional
    public void deleteProcedure(int procedureId) {
        procedureRepository.deleteById(procedureId);
    }

    private ProcedureDTO convertToDto(Procedure procedure) {
        return modelMapper.map(procedure, ProcedureDTO.class);
    }
}
