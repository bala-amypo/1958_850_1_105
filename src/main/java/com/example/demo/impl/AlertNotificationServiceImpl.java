package com.example.demo.service.impl;

import com.example.demo.model.AlertNotification;
import com.example.demo.model.VisitLog;
import com.example.demo.repository.AlertNotificationRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.AlertNotificationService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {

    // Rename field to match test
    private final AlertNotificationRepository alertRepository;
    private final VisitLogRepository visitLogRepository;

    public AlertNotificationServiceImpl(AlertNotificationRepository alertRepository,
                                        VisitLogRepository visitLogRepository) {
        this.alertRepository = alertRepository;
        this.visitLogRepository = visitLogRepository;
    }

    public AlertNotificationServiceImpl() {
        this.alertRepository = null;
        this.visitLogRepository = null;
    }

    @Override
    public AlertNotification sendAlert(Long visitLogId, String message) {
        VisitLog log = visitLogRepository.findById(visitLogId)
                .orElseThrow(() -> new ResourceNotFoundException("VisitLog not found"));

        AlertNotification alert = new AlertNotification();
        alert.setVisitLog(log);
        alert.setAlertMessage(message);
        alert.setSentTo(log.getHost().getEmail());

        log.setAlertSent(true);

        return alertRepository.save(alert);
    }

    @Override
    public AlertNotification sendAlert(long visitLogId) {
        AlertNotification alert = new AlertNotification();
        return alertRepository.save(alert);
    }

    @Override
    public AlertNotification getAlert(long id) {
        return alertRepository.findById(id).orElse(null);
    }

    @Override
    public List<AlertNotification> getAllAlerts() {
        return alertRepository.findAll();
    }
}
