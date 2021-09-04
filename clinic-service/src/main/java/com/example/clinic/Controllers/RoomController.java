package com.example.clinic.Controllers;

import com.example.clinic.DTO.RoomDTO;
import com.example.clinic.Model.Room;
import com.example.clinic.Services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;

    @PostMapping("/{hospitalId}/{doctorId}")
    public Room saveRoom(@PathVariable int hospitalId, @PathVariable int doctorId) {
        return roomService.saveRoom(hospitalId, doctorId);
    }

    @GetMapping
    public List<RoomDTO> getRoom() {
        return roomService.getRooms();
    }

    @PutMapping("/{hospitalId}/{doctorId}")
    public Room updateRoom(@PathVariable int hospitalId, @PathVariable int doctorId) {
        return roomService.updateRoom(hospitalId, doctorId);
    }

    @DeleteMapping("/delete/id/id1")
    public void deleteRoom(@PathVariable int id, @PathVariable int id1) {
        roomService.deleteRoom(id, id1);
    }
}

