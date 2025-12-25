package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Appointment {

    public enum Status { SCHEDULED, COMPLETED, CANCELLED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Visitor visitor;

    @ManyToOne
    private Host host;

    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Visitor getVisitor() { return visitor; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }

    public Host getHost() { return host; }
    public void setHost(Host host) { this.host = host; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
