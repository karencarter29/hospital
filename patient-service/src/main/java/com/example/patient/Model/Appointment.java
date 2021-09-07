package com.example.patient.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name="appointment_shiftId_idx", columnList = "shiftId"),
        @Index(name="appointment_patientId_idx", columnList = "patientId")
})
public class Appointment implements Serializable {

    @Id
    @Column(name="shiftId")
    private UUID id;

    @OneToOne(targetEntity = Shift.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "shiftId")
    @JsonIgnore
    @MapsId
    private Shift shift;

    @ManyToOne(targetEntity = Patient.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    @JsonIgnore
    private Patient patient;


    public Appointment(Shift shift, Patient patient) {
        this.shift = shift;
        this.patient = patient;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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


