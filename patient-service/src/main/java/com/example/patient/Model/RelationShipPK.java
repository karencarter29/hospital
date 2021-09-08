package com.example.patient.Model;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class RelationShipPK implements Serializable {
    private Shift shift;

    public RelationShipPK(Shift shift) {
        this.shift = shift;
    }


    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

}
