package com.example.patient.Services;

import com.example.patient.DTO.PatientDTO;
import com.example.patient.DTO.ShiftDTO;
import com.example.patient.Model.Patient;
import com.example.patient.Model.Shift;
import com.example.patient.Repositories.ShiftRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShiftService {
    private ModelMapper modelMapper;
    private ShiftRepository shiftRepository;

    @Transactional
    public Shift addShift(ShiftDTO shift) //@RequestBody shift
    {
//        List<Shift> shiftList = (List<Shift>)shiftRepository.findAll();
//        for(Shift sh: shiftList) {
//            if(shift.getStartTime().isAfter())
//        }
        return shiftRepository.save(convertToEntity(shift));
    }

    @Transactional(readOnly = true)
    public List<ShiftDTO> getShifts() {
        List<Shift> shiftList = (List<Shift>) shiftRepository.findAll();
        return shiftList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public Shift updateShift(ShiftDTO newShift) //@RequestBody shift
    {
//        newShift.setSpecialityName(specialityName);
//        newShift.setProcedureName(procedureName);
        return shiftRepository.save(convertToEntity(newShift));
    }

    @Transactional
    public void deleteShift(int shiftId) {//@PathVariable id
        shiftRepository.deleteById(shiftId);
    }


    private ShiftDTO convertToDto(Shift shift) {
        return modelMapper.map(shift, ShiftDTO.class);
    }
    private Shift convertToEntity(ShiftDTO shiftDTO) {
        return modelMapper.map(shiftDTO, Shift.class);
    }

}