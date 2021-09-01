package com.example.clinic.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Procedure {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String procedureName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Speciality speciality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedure_name) {
        this.procedureName = procedure_name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}