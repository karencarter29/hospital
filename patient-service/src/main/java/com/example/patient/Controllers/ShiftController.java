package com.example.patient.Controllers;
import com.example.patient.DTO.ShiftDTO;
import com.example.patient.Model.Shift;
import com.example.patient.Services.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/shift")
public class ShiftController {

    private ShiftService shiftService;

    @PostMapping
    public Shift saveShift(@RequestBody ShiftDTO shift) {
        return shiftService.addShift(shift);
    }

    @GetMapping("/{doctorId}")
    public List<ShiftDTO> getShifts(@PathVariable UUID doctorId) {
        return shiftService.getShifts(doctorId);
    }

    @GetMapping
    public List<ShiftDTO> getAllShifts() {
        return shiftService.getAllShifts();
    }

    @PutMapping
    public Shift updateShift(@RequestBody ShiftDTO shift) {
        return shiftService.updateShift(shift);
    }

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable UUID id) {
        shiftService.deleteShift(id);
    }
}
