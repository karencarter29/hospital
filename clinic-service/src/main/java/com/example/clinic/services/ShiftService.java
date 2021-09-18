package com.example.clinic.services;

import com.example.clinic.dto.ShiftDTO;
import com.example.clinic.model.Shift;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "feignShiftService", url = "http://localhost:8082/shift")
public interface ShiftService {
    @PostMapping
    public Shift saveShift(@RequestBody ShiftDTO shift);

    @GetMapping("/{doctorId}")
    public List<ShiftDTO> getShifts(@PathVariable UUID doctorId);

    @GetMapping
    public List<ShiftDTO> getAllShifts();

    @PutMapping
    public Shift updateShift(@RequestBody ShiftDTO shift);

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable UUID id);

}
