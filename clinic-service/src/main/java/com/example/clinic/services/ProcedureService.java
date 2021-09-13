package com.example.clinic.services;



import com.example.clinic.dto.ProcedureDTO;

import com.example.clinic.model.Procedure;
import com.example.clinic.model.Speciality;
import com.example.clinic.repositories.ProcedureRepository;
import com.example.clinic.repositories.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProcedureService {
    private ProcedureRepository procedureRepository;
    private SpecialityRepository specialityRepository;
    private ModelMapper modelMapper;

    @Transactional
    public Procedure addProcedure(ProcedureDTO procedure, UUID speciality) {
        Speciality s = specialityRepository.findById(speciality).orElse(null);
        procedure.setSpeciality(s);
        return procedureRepository.save(convertToEntity(procedure));
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
    public Procedure updateProcedure(ProcedureDTO newProcedure, UUID speciality) {
        return addProcedure(newProcedure, speciality);
    }

    @Transactional
    public void deleteProcedure(UUID procedureId) {
        procedureRepository.deleteById(procedureId);
    }

    private ProcedureDTO convertToDto(Procedure procedure) {
        return modelMapper.map(procedure, ProcedureDTO.class);
    }
    private Procedure convertToEntity(ProcedureDTO procedureDTO) {
        return modelMapper.map(procedureDTO, Procedure.class);
    }
}
