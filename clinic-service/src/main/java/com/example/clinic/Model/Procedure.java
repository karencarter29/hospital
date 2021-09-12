package com.example.clinic.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name="procedure_id_idx", columnList ="id")
})
public class Procedure {
    @Id
    private UUID id;
    private String procedureName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Speciality speciality;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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