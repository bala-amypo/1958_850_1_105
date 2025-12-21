package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AlertNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private VisitLog visitLog;

    private String message;
    private LocalDateTime sentAt;

    public AlertNotification() {}

    // GETTERS
    public Long getId() { return id; }
    public VisitLog getVisitLog() { return visitLog; }
    public String getMessage() { return message; }
    public LocalDateTime getSentAt() { return sentAt; }

    // SETTERS (MANDATORY)
    public void setId(Long id) { this.id = id; }
    public void setVisitLog(VisitLog visitLog) {
        this.visitLog = visitLog;
    }
    public void setMessage(String message) { this.message = message; }
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
