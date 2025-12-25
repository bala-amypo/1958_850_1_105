package com.example.demo.dto;

import com.example.demo.entity.VisitLog;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitLogDTO {
    private Long id;
    private Long visitorId;
    private Long hostId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String purpose;
    private Boolean accessGranted;
    private Boolean alertSent;

    public VisitLogDTO(VisitLog log) {
        this.id = log.getId();
        this.visitorId = log.getVisitor() != null ? log.getVisitor().getId() : null;
        this.hostId = log.getHost() != null ? log.getHost().getId() : null;
        this.checkInTime = log.getCheckInTime();
        this.checkOutTime = log.getCheckOutTime();
        this.purpose = log.getPurpose();
        this.accessGranted = log.getAccessGranted();
        this.alertSent = log.getAlertSent();
    }
}
