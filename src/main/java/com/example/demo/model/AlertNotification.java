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
    public void onSend() {
        this.sentAt = LocalDateTime.now();
    }

    // getters & setters
}
