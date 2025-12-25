package com.example.demo.service;
import com.example.demo.dto.AlertNotificationDTO;
import java.util.List;

public interface AlertNotificationService {
    AlertNotificationDTO sendAlert(Long visitLogId, AlertNotificationDTO alertDTO);
    List<AlertNotificationDTO> getAllAlerts();
    AlertNotificationDTO getAlertById(Long id);
    AlertNotificationDTO getAlertByVisitLogId(Long visitLogId);
}
