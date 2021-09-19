package com.example.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
