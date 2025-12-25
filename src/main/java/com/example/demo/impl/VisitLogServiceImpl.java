package com.example.demo.impl;

import com.example.demo.entity.VisitLog;
import com.example.demo.entity.Visitor;
import com.example.demo.entity.Host;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;

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

    @Override
    public VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose) {
        Visitor visitor = visitorRepository.findById(visitorId)
                .orElseThrow(() -> new RuntimeException("Visitor not found"));
        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new RuntimeException("Host not found"));
        
        VisitLog visitLog = new VisitLog();
        visitLog.setVisitor(visitor);
        visitLog.setHost(host);
        visitLog.setPurpose(purpose);
        visitLog.setAccessGranted(true);
        visitLog.setAlertSent(false);
        return visitLogRepository.save(visitLog);
    }

    @Override
    public VisitLog checkOutVisitor(Long visitLogId) {
        VisitLog visitLog = visitLogRepository.findById(visitLogId)
                .orElseThrow(() -> new RuntimeException("VisitLog not found"));
        if (visitLog.getCheckOutTime() != null) {
            throw new RuntimeException("Visitor not checked in");
        }
        visitLog.setCheckOutTime(java.time.LocalDateTime.now());
        return visitLogRepository.save(visitLog);
    }
}
