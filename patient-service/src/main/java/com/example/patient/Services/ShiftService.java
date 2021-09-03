package com.example.patient.Services;


import com.example.patient.DTO.ShiftDTO;
import com.example.patient.Model.Shift;
import com.example.patient.Repositories.AppointmentRepository;
import com.example.patient.Repositories.ShiftRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShiftService {
    private ModelMapper modelMapper;
    private AppointmentRepository appointmentRepository;
    private ShiftRepository shiftRepository;

    public Shift addShift(Shift shift) //@RequestBody shift
    {
        //shift.setDoctorId(idDoctor);
        return shiftRepository.save(shift);
    }

    public List<ShiftDTO> getShifts() {
        List<Shift> shiftList = (List<Shift>) shiftRepository.findAll();
        return shiftList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //
    public Shift updateShift(Shift newShift) //@RequestBody shift
    {
        return shiftRepository.save(newShift);
    }

    //
    public void deleteShift(int shiftId) {//@PathVariable id
        shiftRepository.deleteById(shiftId);
    }

    //
    private ShiftDTO convertToDto(Shift shift) {
        return modelMapper.map(shift, ShiftDTO.class);
    }

}