package com.example.clinic.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="doctor")
public class Doctor{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int  id;
    private int userId;

    private String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    private Speciality speciality;
//    @OneToOne(//mappedBy = "doctor", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY)
//    private Room room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
}
