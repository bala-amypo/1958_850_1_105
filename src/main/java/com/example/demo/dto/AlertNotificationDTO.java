package com.example.demo.dto;

import com.example.demo.entity.AlertNotification;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertNotificationDTO {
    private Long id;
    private Long visitLogId;
    private String alertMessage;
    private String sentTo;
    private LocalDateTime sentAt;

    public AlertNotificationDTO(AlertNotification alert) {
        this.id = alert.getId();
        this.visitLogId = alert.getVisitLog() != null ? alert.getVisitLog().getId() : null;
        this.alertMessage = alert.getAlertMessage();
        this.sentTo = alert.getSentTo();
        this.sentAt = alert.getSentAt();
    }
}
