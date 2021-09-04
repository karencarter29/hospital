package com.example.clinic.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RelationShipPK.class)
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

    private Long roomNumber;


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

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }
}