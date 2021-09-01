package com.example.clinic.Services;


import com.example.clinic.DTO.ProcedureDTO;

import com.example.clinic.Model.Procedure;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Repositories.ProcedureRepository;
import com.example.clinic.Repositories.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProcedureService {
    private ProcedureRepository procedureRepository;
    private SpecialityRepository specialityRepository;
    private ModelMapper modelMapper;

    public Procedure addProcedure(Procedure procedure) {
        return procedureRepository.save(procedure);
    }

    public List<ProcedureDTO> getProcedures() {
        List<Procedure> procedures = (List<Procedure>) procedureRepository.findAll();
        return procedures.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List<String> getProcedureNames() {
        List<Procedure> procedures = (List<Procedure>) procedureRepository.findAll();
        List<String> names = new LinkedList<>();
        for(Procedure p: procedures) {
          names.add(p.getProcedureName());
        }
        return names;
    }

    public Procedure updateProcedure(Procedure newProcedure) {
        return procedureRepository.save(newProcedure);
    }

    public void deleteProcedure(int procedureId) {
        procedureRepository.deleteById(procedureId);
    }

    private ProcedureDTO convertToDto(Procedure procedure) {
        return modelMapper.map(procedure, ProcedureDTO.class);
    }
}
