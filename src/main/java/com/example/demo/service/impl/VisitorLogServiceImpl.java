package com.example.demo.service.impl;

import com.example.demo.dto.VisitLogDTO;
import com.example.demo.entity.VisitLog;
import com.example.demo.entity.Visitor;
import com.example.demo.entity.Host;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitLogServiceImpl implements VisitLogService {

    private final VisitLogRepository visitLogRepository;
    private final VisitorRepository visitorRepository;
    private final HostRepository hostRepository;

    public VisitLogServiceImpl(VisitLogRepository visitLogRepository, VisitorRepository visitorRepository, HostRepository hostRepository) {
        this.visitLogRepository = visitLogRepository;
        this.visitorRepository = visitorRepository;
        this.hostRepository = hostRepository;
    }

    // FIXED: Interface expects VisitLogDTO parameter
    public VisitLogDTO checkInVisitor(VisitLogDTO visitLogDTO) {
        Visitor visitor = visitorRepository.findById(visitLogDTO.getVisitorId())
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
        Host host = hostRepository.findById(visitLogDTO.getHostId())
                .orElseThrow(() -> new RuntimeException("Host not found"));
        
        VisitLog visitLog = new VisitLog();
        visitLog.setVisitor(visitor);
        visitLog.setHost(host);
        visitLog.setPurpose(visitLogDTO.getPurpose());
        visitLog.setAccessGranted(true);
        visitLog.setAlertSent(false);
        VisitLog saved = visitLogRepository.save(visitLog);
        return new VisitLogDTO(saved.getId(), visitLogDTO.getVisitorId(), visitLogDTO.getHostId(), 
                              saved.getCheckInTime(), null, visitLogDTO.getPurpose(), true, false);
    }

    public VisitLogDTO checkOutVisitor(Long visitLogId) {
        VisitLog visitLog = visitLogRepository.findById(visitLogId)
                .orElseThrow(() -> new RuntimeException("VisitLog not found"));
        if (visitLog.getCheckOutTime() != null) {
            throw new RuntimeException("Visitor not checked in");
        }
        visitLog.setCheckOutTime(java.time.LocalDateTime.now());
        VisitLog saved = visitLogRepository.save(visitLog);
        return new VisitLogDTO(saved.getId(), saved.getVisitor().getId(), saved.getHost().getId(), 
                              saved.getCheckInTime(), saved.getCheckOutTime(), saved.getPurpose(), 
                              saved.getAccessGranted(), saved.getAlertSent());
    }

    public List<VisitLogDTO> getActiveVisits() {
        return visitLogRepository.findByCheckOutTimeIsNull()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VisitLogDTO getVisitLogById(Long id) {
        VisitLog visitLog = visitLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VisitLog not found"));
        return toDTO(visitLog);
    }

    public List<VisitLogDTO> getAllVisitLogs() {
        return visitLogRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<VisitLogDTO> getVisitLogsByVisitorId(Long visitorId) {
        return visitLogRepository.findByVisitorId(visitorId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<VisitLogDTO> getVisitLogsByHostId(Long hostId) {
        return visitLogRepository.findByHostId(hostId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private VisitLogDTO toDTO(VisitLog visitLog) {
        return new VisitLogDTO(visitLog.getId(), visitLog.getVisitor().getId(), 
                              visitLog.getHost().getId(), visitLog.getCheckInTime(), 
                              visitLog.getCheckOutTime(), visitLog.getPurpose(), 
                              visitLog.getAccessGranted(), visitLog.getAlertSent());
    }
}
