package com.example.clinic.model;

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
    @GeneratedValue(generator = "hibernate-uuid")
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

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}