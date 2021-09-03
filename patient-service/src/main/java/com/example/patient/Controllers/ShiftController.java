package com.example.patient.Controllers;

import com.example.patient.DTO.ShiftDTO;
import com.example.patient.Model.Shift;
import com.example.patient.Services.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/shift")
public class ShiftController {

    private ShiftService shiftService;

    @PostMapping//(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Shift saveShift(@RequestBody Shift shift) {
        return shiftService.addShift(shift);
    }
    //
    @GetMapping
    public List<ShiftDTO> getShifts() {
        return shiftService.getShifts();
    }

    @PutMapping
    public Shift updateShift(@RequestBody Shift shift) {
        return shiftService.updateShift(shift);
    }
    //
    @DeleteMapping("/delete/{id}")
    public void deleteShift(@PathVariable int id) {
        shiftService.deleteShift(id);
    }

}