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
public class Speciality {
    @Id
    private UUID id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
