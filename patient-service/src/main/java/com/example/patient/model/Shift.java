package com.example.patient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name="shift_doctorId_idx", columnList = "doctorId")
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shift {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID doctorId;
    private String specialityName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String procedureName;
    @OneToOne(mappedBy = "shift", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @PrimaryKeyJoinColumn
    private Appointment appointment;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", specialityName='" + specialityName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", procedureName='" + procedureName + '\'' +
                '}';
    }
}