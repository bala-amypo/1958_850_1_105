package com.example.demo.service.impl;

import com.example.demo.entity.AlertNotification;
import com.example.demo.entity.VisitLog;
import com.example.demo.exception.IllegalArgumentException;
import com.example.demo.repository.AlertNotificationRepository;
import com.example.demo.service.AlertNotificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {

    private final AlertNotificationRepository alertRepository;

    public AlertNotificationServiceImpl(AlertNotificationRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public AlertNotification sendAlert(VisitLog visitLog, String message) {
        Optional<AlertNotification> existing = alertRepository.findByVisitLogId(visitLog.getId());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Duplicate alert for this visit");
        }

        AlertNotification alert = new AlertNotification();
        alert.setVisitLog(visitLog);
        alert.setMessage(message);
        alert.setSent(true);
        alert.setSentAt(new java.util.Date());

        return alertRepository.save(alert);
    }
}
