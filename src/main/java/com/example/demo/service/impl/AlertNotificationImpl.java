package com.example.demo.service.impl;

import com.example.demo.model.AlertNotification;
import com.example.demo.model.VisitLog;
import com.example.demo.repository.AlertNotificationRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.AlertNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {
    
    private AlertNotificationRepository alertRepository;
    private VisitLogRepository visitLogRepository;
    
    public AlertNotificationServiceImpl() {}
    
    @Autowired
    public void setAlertRepository(AlertNotificationRepository alertRepository) {
        this.alertRepository = alertRepository;
    }
    
    @Autowired
    public void setVisitLogRepository(VisitLogRepository visitLogRepository) {
        this.visitLogRepository = visitLogRepository;
    }
    
    @Override
    public AlertNotification sendAlert(Long visitLogId) {
        VisitLog visitLog = visitLogRepository.findById(visitLogId)
            .orElseThrow(() -> new RuntimeException("VisitLog not found"));
        
        if (alertRepository.findByVisitLogId(visitLogId).isPresent()) {
            throw new IllegalArgumentException("Alert already sent for this visit log");
        }
        
        AlertNotification alert = new AlertNotification();
        alert.setVisitLog(visitLog);
        alert.setAlertMessage("Visitor " + visitLog.getVisitor().getFullName() + 
                             " checked in for " + visitLog.getPurpose());
        alert.setSentTo(visitLog.getHost().getEmail());
        alert.setSentAt(LocalDateTime.now());
        
        AlertNotification saved = alertRepository.save(alert);
        
        // Set alert flag on visit log
        visitLog.setAlertSent(true);
        visitLogRepository.save(visitLog);
        
        return saved;
    }
    
    @Override
    public AlertNotification getAlert(Long id) {
        return alertRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alert not found"));
    }
    
    @Override
    public List<AlertNotification> getAllAlerts() {
        return alertRepository.findAll();
    }
}
