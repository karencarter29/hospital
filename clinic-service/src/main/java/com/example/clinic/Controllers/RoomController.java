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

    @PostMapping
    public Room saveRoom(@RequestBody RoomDTO room) throws ParseException {
        return roomService.saveRoom(room);
    }

    @GetMapping
    public List<RoomDTO> getRoom() {
        return roomService.getRooms();
    }

    @PutMapping
    public Room updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/delete/id/id1")
    public void deleteRoom(@PathVariable int id, @PathVariable int id1) {
        roomService.deleteRoom(id, id1);
    }
}

