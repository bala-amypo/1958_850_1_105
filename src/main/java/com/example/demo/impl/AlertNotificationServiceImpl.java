package com.example.demo.service.impl;

import com.example.demo.model.AlertNotification;
import com.example.demo.service.AlertNotificationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {

    @Override
    public List<AlertNotification> getAllAlerts() {
        // Your test expects some sample return, adjust accordingly
        return List.of(); // empty list for now
    }
}
