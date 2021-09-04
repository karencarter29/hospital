package com.example.patient.Model;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class RelationShipPK implements Serializable {
    private Shift shift;
    private Patient patient;

    public RelationShipPK(Shift shift, Patient patient) {
        this.shift = shift;
        this.patient = patient;
    }


    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
