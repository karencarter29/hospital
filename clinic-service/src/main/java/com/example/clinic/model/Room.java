package com.example.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.io.Serializable;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RelationShipPK.class)
@Table(indexes = {
        @Index(name = "room_hospitalId_idx", columnList = "hospitalId"),
        @Index(name = "room_doctorId_idx", columnList = "doctorId")
})
public class Room implements Serializable {

    @Id
    @ManyToOne(targetEntity = Hospital.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospitalId")
    @JsonIgnore
    private Hospital hospital;


    @Id
    @OneToOne(targetEntity = Doctor.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    @JsonIgnore
    private Doctor doctor;

    private String roomNumber;

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}