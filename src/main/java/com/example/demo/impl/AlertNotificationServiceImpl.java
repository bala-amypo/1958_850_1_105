package com.example.demo.service.impl;
import com.example.demo.dto.AlertNotificationDTO;
import com.example.demo.entity.AlertNotification;
import com.example.demo.entity.VisitLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.AlertNotificationRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.AlertNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {
    @Autowired
    private AlertNotificationRepository alertNotificationRepository;

    @Autowired
    private VisitLogRepository visitLogRepository;

    @Override
    public AlertNotificationDTO sendAlert(Long visitLogId, AlertNotificationDTO alertDTO) {
        VisitLog visitLog = visitLogRepository.findById(visitLogId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit log not found"));

        // Check if alert already sent for this visit
        if (alertNotificationRepository.findByVisitLogId(visitLogId).isPresent()) {
            throw new BadRequestException("Alert already sent for this visit");
        }

        AlertNotification alertNotification = new AlertNotification();
        alertNotification.setVisitLog(visitLog);
        alertNotification.setSentTo(alertDTO.getSentTo());
        alertNotification.setAlertMessage(alertDTO.getAlertMessage());

        AlertNotification savedAlert = alertNotificationRepository.save(alertNotification);
        visitLog.setAlertSent(true);
        visitLogRepository.save(visitLog);

        return mapToDTO(savedAlert);
    }

    @Override
    public List<AlertNotificationDTO> getAllAlerts() {
        return alertNotificationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlertNotificationDTO getAlertById(Long id) {
        AlertNotification alert = alertNotificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found with id: " + id));
        return mapToDTO(alert);
    }

    @Override
    public AlertNotificationDTO getAlertByVisitLogId(Long visitLogId) {
        AlertNotification alert = alertNotificationRepository.findByVisitLogId(visitLogId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found for visit log id: " + visitLogId));
        return mapToDTO(alert);
    }

    private AlertNotificationDTO mapToDTO(AlertNotification alert) {
        return new AlertNotificationDTO(alert.getId(), alert.getVisitLog().getId(),
                alert.getSentTo(), alert.getAlertMessage(), alert.getSentAt());
    }
}
