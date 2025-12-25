package com.example.demo.service;

import com.example.demo.model.AlertNotification;
import java.util.List;

public interface AlertNotificationService {
    AlertNotification sendAlert(Long visitLogId, String message);
    AlertNotification sendAlert(long visitLogId); // for tests
    AlertNotification getAlert(long id); // for tests
    List<AlertNotification> getAllAlerts(); // for tests
}
