package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Visitor visitor;

    @ManyToOne
    private Host host;

    private LocalDate appointmentDate;
    private String purpose;
    private String status;

    @PrePersist
    public void onCreate() { if (status == null) status = "SCHEDULED"; }

    public Appointment() {}

    // getters
    public Long getId() { return id; }
    public Visitor getVisitor() { return visitor; }
    public Host getHost() { return host; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public String getPurpose() { return purpose; }
    public String getStatus() { return status; }

    // setters
    public void setId(long id) { this.id = id; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }
    public void setHost(Host host) { this.host = host; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public void setStatus(String status) { this.status = status; }
}
