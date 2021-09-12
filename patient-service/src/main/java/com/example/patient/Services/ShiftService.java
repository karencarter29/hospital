package com.example.patient.Services;
import com.example.patient.DTO.ShiftDTO;
import com.example.patient.Model.Shift;
import com.example.patient.Repositories.ShiftRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShiftService {
    private ModelMapper modelMapper;
    private ShiftRepository shiftRepository;

    @Transactional
    public Shift addShift(ShiftDTO shift) {
        List<Shift> startTime = shiftRepository.forStartTime(shift.getStartTime(), shift.getEndTime());
        List<Shift> endTime = shiftRepository.forEndTime(shift.getStartTime(), shift.getEndTime());
        if (shift.getStartTime().isBefore(shift.getEndTime())) {
            if (startTime.isEmpty() && endTime.isEmpty()) {
                return shiftRepository.save(convertToEntity(shift));
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<ShiftDTO> getShifts(UUID doctorId) {
        List<Shift> shiftList = shiftRepository.getShift(doctorId);
        return shiftList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ShiftDTO> getAllShifts() {
        List<Shift> shiftList = shiftRepository.findAll();
        return shiftList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public Shift updateShift(ShiftDTO newShift) //@RequestBody shift
    {
        return shiftRepository.save(convertToEntity(newShift));
    }

    @Transactional
    public void deleteShift(UUID shiftId) {//@PathVariable id
        shiftRepository.deleteById(shiftId);
    }


    private ShiftDTO convertToDto(Shift shift) {
        return modelMapper.map(shift, ShiftDTO.class);
    }

    private Shift convertToEntity(ShiftDTO shiftDTO) {
        return modelMapper.map(shiftDTO, Shift.class);
    }

}