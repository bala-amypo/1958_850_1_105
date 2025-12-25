package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_notifications")
public class AlertNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private VisitLog visitLog;

    private String sentTo;
    private String alertMessage;
    private LocalDateTime sentAt;

    @PrePersist
    public void onSend() { this.sentAt = LocalDateTime.now(); }

    public AlertNotification() {}

    // getters
    public Long getId() { return id; }
    public VisitLog getVisitLog() { return visitLog; }
    public String getSentTo() { return sentTo; }
    public String getAlertMessage() { return alertMessage; }
    public LocalDateTime getSentAt() { return sentAt; }

    // setters
    public void setId(long id) { this.id = id; }
    public void setVisitLog(VisitLog visitLog) { this.visitLog = visitLog; }
    public void setSentTo(String sentTo) { this.sentTo = sentTo; }
    public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
