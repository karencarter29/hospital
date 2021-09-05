package com.example.clinic.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Speciality {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String specialityName;
    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();
    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    private List<Procedure> procedures = new ArrayList<>();

    public void addDoctor(Doctor doctor)
    {
        doctor.setSpeciality(this);
        doctors.add(doctor);
    }
    public void addProcedures(Procedure procedure)
    {
        procedure.setSpeciality(this);
        procedures.add(procedure);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpeciality_name(String speciality_name) {
        this.specialityName = speciality_name;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
