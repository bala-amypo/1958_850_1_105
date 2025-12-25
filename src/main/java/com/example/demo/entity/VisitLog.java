package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @Column(name = "check_in_time", nullable = false)
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time", nullable = true)
    private LocalDateTime checkOutTime;

    @NotBlank
    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "access_granted", nullable = false)
    private Boolean accessGranted;

    @Column(name = "alert_sent", nullable = false)
    private Boolean alertSent = false;

    @PrePersist
    protected void onCreate() {
        this.checkInTime = LocalDateTime.now();
        if (this.alertSent == null) this.alertSent = false;
    }
}
