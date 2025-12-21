package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Visitor visitor;

    @ManyToOne
    private Host host;

    // 🔥 MUST be LocalDateTime (service expects this)
    private LocalDateTime appointmentDate;

    private String status;

    public Appointment() {}

    // GETTERS
    public Long getId() { return id; }
    public Visitor getVisitor() { return visitor; }
    public Host getHost() { return host; }
    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }
    public String getStatus() { return status; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }
    public void setHost(Host host) { this.host = host; }

    // ✅ NORMAL SETTER (used by service)
    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    // ✅ EXTRA SETTER (used by tests / JSON binding)
    public void setAppointmentDate(LocalDate appointmentDate) {
        if (appointmentDate != null) {
            this.appointmentDate = appointmentDate.atStartOfDay();
        }
    }

    public void setStatus(String status) { this.status = status; }
}
