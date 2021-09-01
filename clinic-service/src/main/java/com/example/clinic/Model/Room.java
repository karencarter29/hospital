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

    private Long room_number;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }


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

    public Long getRoom_number() {
        return room_number;
    }

    public void setRoom_number(Long room_number) {
        this.room_number = room_number;
    }
}