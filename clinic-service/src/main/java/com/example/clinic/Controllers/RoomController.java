package com.example.clinic.Controllers;

import com.example.clinic.DTO.RoomDTO;
import com.example.clinic.Model.Room;
import com.example.clinic.Services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;

    @PostMapping("/{hospitalId}/{doctorId}")
    public Room saveRoom(@RequestBody String roomNumber, @PathVariable UUID hospitalId, @PathVariable UUID doctorId) {
        return roomService.saveRoom( hospitalId, doctorId, roomNumber);
    }

    @GetMapping
    public List<RoomDTO> getRoom() {
        return roomService.getRooms();
    }

    @PutMapping("/{hospitalId}/{doctorId}")
    public Room updateRoom(@RequestBody String roomNumber, @PathVariable UUID hospitalId, @PathVariable UUID doctorId) {
        return roomService.updateRoom(hospitalId, doctorId, roomNumber );
    }

    @DeleteMapping("/{id}/{id1}")
    public void deleteRoom(@PathVariable UUID id, @PathVariable UUID id1) {
        roomService.deleteRoom(id, id1);
    }
}

