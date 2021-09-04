package com.example.clinic.Model;


import java.io.Serializable;

//@Embeddable
public class RelationShipPK implements Serializable {
    private Hospital hospital;
    private Doctor doctor;

    public RelationShipPK(Hospital hospital, Doctor doctor) {
        this.hospital = hospital;
        this.doctor = doctor;
    }

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
}
