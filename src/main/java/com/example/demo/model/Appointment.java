package com.example.demo.model;

import java.time.LocalDateTime;

public class Appointment {

    private Long id;
    private Visitor visitor;   // reference to Visitor model
    private Host host;         // reference to Host model
    private LocalDateTime appointmentTime;
    private String purpose;

    // ===== Constructors =====
    public Appointment() {}

    public Appointment(Long id, Visitor visitor, Host host, LocalDateTime appointmentTime, String purpose) {
        this.id = id;
        this.visitor = visitor;
        this.host = host;
        this.appointmentTime = appointmentTime;
        this.purpose = purpose;
    }

    // ===== Getters & Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
