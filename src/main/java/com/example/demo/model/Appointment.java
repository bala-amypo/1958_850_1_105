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
    public void onCreate() {
        if (this.status == null) {
            this.status = "SCHEDULED";
        }
    }

    // getters & setters
}
