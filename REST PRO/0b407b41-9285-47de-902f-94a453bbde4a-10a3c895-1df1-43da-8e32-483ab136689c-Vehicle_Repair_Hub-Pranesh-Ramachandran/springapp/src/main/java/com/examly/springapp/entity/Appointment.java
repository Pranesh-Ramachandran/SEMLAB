package com.examly.springapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appointments")
@Getter
@Setter

public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore 
    private User user; 

    @ManyToOne
    @JoinColumn(name = "repair_shop_id", nullable = false)
    private String repairshop;  // Changed from String to RepairShop

    private LocalDate appointmentDate;
    private String serviceRequested;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRepairShop() {
        return repairshop;
    }

    public void setRepairShop(String repairShop) {
        this.repairshop = repairShop;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getServiceRequested() {
        return serviceRequested;
    }

    public void setServiceRequested(String serviceRequested) {
        this.serviceRequested = serviceRequested;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}