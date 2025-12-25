package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AlertNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private VisitLog visitLog;

    private String message;
    private boolean sent;
    private Date sentAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VisitLog getVisitLog() { return visitLog; }
    public void setVisitLog(VisitLog visitLog) { this.visitLog = visitLog; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSent() { return sent; }
    public void setSent(boolean sent) { this.sent = sent; }

    public Date getSentAt() { return sentAt; }
    public void setSentAt(Date sentAt) { this.sentAt = sentAt; }
}
