package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit_logs")
public class VisitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Visitor visitor;

    @ManyToOne
    private Host host;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String purpose;
    private Boolean accessGranted;
    private Boolean alertSent;

    @PrePersist
    public void onCheckIn() { this.checkInTime = LocalDateTime.now(); this.alertSent = false; }

    public VisitLog() {}

    // getters
    public Long getId() { return id; }
    public Visitor getVisitor() { return visitor; }
    public Host getHost() { return host; }
    public LocalDateTime getCheckInTime() { return checkInTime; }
    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public String getPurpose() { return purpose; }
    public Boolean getAccessGranted() { return accessGranted; }
    public Boolean getAlertSent() { return alertSent; }

    // setters
    public void setId(long id) { this.id = id; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }
    public void setHost(Host host) { this.host = host; }
    public void setCheckInTime(LocalDateTime time) { this.checkInTime = time; }
    public void setCheckOutTime(LocalDateTime time) { this.checkOutTime = time; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public void setAccessGranted(Boolean accessGranted) { this.accessGranted = accessGranted; }
    public void setAlertSent(Boolean alertSent) { this.alertSent = alertSent; }
}
