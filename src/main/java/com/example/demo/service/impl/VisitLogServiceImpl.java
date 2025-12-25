package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitLogServiceImpl implements VisitLogService {
    
    VisitLogRepository visitLogRepository;
    VisitorRepository visitorRepository;
    HostRepository hostRepository;

    public VisitLogServiceImpl() {}

    @Autowired
    public VisitLogServiceImpl(VisitLogRepository visitLogRepository, VisitorRepository visitorRepository, HostRepository hostRepository) {
        this.visitLogRepository = visitLogRepository;
        this.visitorRepository = visitorRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose) {
        Visitor visitor = visitorRepository.findById(visitorId).orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        Host host = hostRepository.findById(hostId).orElseThrow(() -> new ResourceNotFoundException("Host not found"));
        VisitLog visitLog = new VisitLog();
        visitLog.setVisitor(visitor);
        visitLog.setHost(host);
        visitLog.setCheckInTime(LocalDateTime.now());
        visitLog.setPurpose(purpose);
        visitLog.setAccessGranted(true);
        visitLog.setAlertSent(false);
        return visitLogRepository.save(visitLog);
    }

    @Override
    public VisitLog checkOutVisitor(Long visitLogId) {
        VisitLog visitLog = visitLogRepository.findById(visitLogId).orElseThrow(() -> new ResourceNotFoundException("VisitLog not found"));
        if (visitLog.getCheckOutTime() != null) throw new IllegalStateException("Visitor not checked in");
        visitLog.setCheckOutTime(LocalDateTime.now());
        return visitLogRepository.save(visitLog);
    }

    @Override
    public List<VisitLog> getAllVisitLogs() { return visitLogRepository.findAll(); }
    @Override
    public VisitLog getVisitLogById(Long id) { return visitLogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("VisitLog not found")); }
    @Override
    public List<VisitLog> getActiveVisits() { return visitLogRepository.findByCheckOutTimeIsNull(); }
    @Override
    public List<VisitLog> getVisitLogsByHostId(Long hostId) { return visitLogRepository.findByHostId(hostId); }
    @Override
    public List<VisitLog> getVisitLogsByVisitorId(Long visitorId) { return visitLogRepository.findByVisitorId(visitorId); }
}
