package com.example.clinic.Services;

import com.example.clinic.DTO.RoomDTO;
import com.example.clinic.Model.Doctor;
import com.example.clinic.Model.Hospital;
import com.example.clinic.Model.RelationShipPK;
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

    public Room saveRoom(RoomDTO room) throws ParseException {
        Room r = convertToEntity(room);
        roomRepository.saveRoom(r.getHospital().getId(), r.getDoctor().getId());
        return r;
    }

    public List<RoomDTO> getRooms() {
        List<Room> roomList = (List<Room>) roomRepository.findAll();
        return roomList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(int hospitalId, int doctorId) {
        roomRepository.deleteRoom(hospitalId, doctorId);
    }

    private RoomDTO convertToDto(Room room) {
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        return roomDTO;
    }

    private Room convertToEntity(RoomDTO roomDTO) throws ParseException {
        Room room = modelMapper.map(roomDTO, Room.class);

        if (roomDTO.getDoctor().getId() != 0 && roomDTO.getHospital().getId() != 0) {
            Doctor doctor = doctorRepository.findById(roomDTO.getDoctor().getId()).get();
            Hospital hospital = hospitalRepository.findById(roomDTO.getHospital().getId()).get();
            room.setDoctor(doctor);
            room.setHospital(hospital);
        }
        return room;
    }
}
