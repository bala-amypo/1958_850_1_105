package com.example.demo.model;

import java.time.LocalDateTime;

public class AlertNotification {

    private Long id;
    private VisitLog visitLog;   // Add this field
    private String sentTo;
    private String alertMessage;
    private LocalDateTime createdAt;

    // ===== Constructors =====
    public AlertNotification() {}

    public AlertNotification(Long id, VisitLog visitLog, String sentTo, String alertMessage, LocalDateTime createdAt) {
        this.id = id;
        this.visitLog = visitLog;
        this.sentTo = sentTo;
        this.alertMessage = alertMessage;
        this.createdAt = createdAt;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VisitLog getVisitLog() { return visitLog; }
    public void setVisitLog(VisitLog visitLog) { this.visitLog = visitLog; }

    public String getSentTo() { return sentTo; }
    public void setSentTo(String sentTo) { this.sentTo = sentTo; }

    public String getAlertMessage() { return alertMessage; }
    public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
