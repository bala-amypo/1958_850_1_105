package com.example.demo.service;

import com.example.demo.model.AlertNotification;

public interface AlertNotificationService {

    AlertNotification sendAlert(Long visitLogId, String message);
}
