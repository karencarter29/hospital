package com.example.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId
    private Shift shift;

    @ManyToOne(targetEntity = Patient.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private Patient patient;


    public Appointment(Shift shift, Patient patient) {
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


