package com.example.demo.model;

import java.time.LocalDateTime;

public class AlertNotification {

    private Long id;
    private String sentTo;       // This is required
    private String alertMessage; // This is required
    private LocalDateTime createdAt;

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSentTo() { return sentTo; }
    public void setSentTo(String sentTo) { this.sentTo = sentTo; }

    public String getAlertMessage() { return alertMessage; }
    public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
