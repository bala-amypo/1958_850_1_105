package com.example.demo.service;

import com.example.demo.entity.AlertNotification;
import java.util.List;

public interface AlertNotificationService {
    List<AlertNotification> getAllAlerts();
    AlertNotification createAlert(AlertNotification alert);
}
