package com.example.clinic.Services;

import com.example.clinic.DTO.RoomDTO;
import com.example.clinic.Model.Doctor;
import com.example.clinic.Model.Hospital;
import com.example.clinic.Model.Room;
import com.example.clinic.Repositories.DoctorRepository;
import com.example.clinic.Repositories.HospitalRepository;
import com.example.clinic.Repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoomService {
    private RoomRepository roomRepository;
    private HospitalRepository hospitalRepository;
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;

    public Room saveRoom(int hospitalId, int doctorId) {
       // Room r = convertToEntity(room);
        Hospital h = hospitalRepository.findById(hospitalId).orElse(null);
        Doctor d = doctorRepository.findById(doctorId).orElse(null);
        Room r = new Room();
        r.setHospital(h);
        r.setDoctor(d);
        roomRepository.saveRoom(hospitalId, doctorId);
        return r;
    }

    public List<RoomDTO> getRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Room updateRoom(int hospitalId, int doctorId) {
        Hospital h = hospitalRepository.findById(hospitalId).orElse(null);
        Doctor d = doctorRepository.findById(doctorId).orElse(null);
        Room r = new Room();
        r.setHospital(h);
        r.setDoctor(d);
        roomRepository.saveRoom(hospitalId, doctorId);
        return null;
    }

    public void deleteRoom(int hospitalId, int doctorId) {
        roomRepository.deleteRoom(hospitalId, doctorId);
    }

    private RoomDTO convertToDto(Room room) {
        return modelMapper.map(room, RoomDTO.class);
    }

    private Room convertToEntity(RoomDTO roomDTO) {
        Room room = modelMapper.map(roomDTO, Room.class);

        if (roomDTO.getDoctor().getId() != 0 && roomDTO.getHospital().getId() != 0) {
            Doctor doctor = doctorRepository.findById(roomDTO.getDoctor().getId()).orElse(null);
            Hospital hospital = hospitalRepository.findById(roomDTO.getHospital().getId()).orElse(null);
            room.setDoctor(doctor);
            room.setHospital(hospital);
        }
        return room;
    }
}
