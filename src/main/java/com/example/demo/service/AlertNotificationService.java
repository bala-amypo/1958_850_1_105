package com.example.demo.service;

import com.example.demo.entity.AlertNotification;
import java.util.List;

public interface AlertNotificationService {
    AlertNotification createAlert(AlertNotification alertNotification);
    List<AlertNotification> getAllAlerts();
}
