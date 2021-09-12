package com.example.clinic.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    @Id
    private UUID id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();
    public void addRoom(Room room) {
        room.setHospital(this);
        rooms.add(room);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}