package com.example.clinic.services;

import com.example.clinic.dto.RoomDTO;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Hospital;
import com.example.clinic.model.Room;
import com.example.clinic.repositories.DoctorRepository;
import com.example.clinic.repositories.HospitalRepository;
import com.example.clinic.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoomService {
    private RoomRepository roomRepository;
    private HospitalRepository hospitalRepository;
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;

    @Transactional
    public Room saveRoom(UUID hospitalId, UUID doctorId, String roomNumber) {
        Hospital h = hospitalRepository.findById(hospitalId).orElse(null);
        Doctor d = doctorRepository.findById(doctorId).orElse(null);
        Room r = new Room(h, d, roomNumber);
        roomRepository.saveRoom(hospitalId, doctorId, roomNumber);
        return r;
    }

    @Transactional(readOnly = true)
    public List<RoomDTO> getRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public Room updateRoom(UUID hospitalId, UUID doctorId, String roomNumber) {
        Hospital h = hospitalRepository.findById(hospitalId).orElse(null);
        Doctor d = doctorRepository.findById(doctorId).orElse(null);
        Room r = new Room(h, d, roomNumber);
        roomRepository.updateRoom(hospitalId, doctorId, roomNumber);
       return r;
    }

    @Transactional
    public void deleteRoom(UUID hospitalId, UUID doctorId) {
        roomRepository.deleteRoom(hospitalId, doctorId);
    }

    private RoomDTO convertToDto(Room room) {
        return modelMapper.map(room, RoomDTO.class);
    }

}
