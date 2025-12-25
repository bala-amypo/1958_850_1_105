package com.example.demo.impl;

import com.example.demo.dto.VisitLogDTO;
import com.example.demo.entity.Host;
import com.example.demo.entity.VisitLog;
import com.example.demo.entity.Visitor;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitLogServiceImpl implements VisitLogService {

    private final VisitLogRepository visitLogRepository;
    private final HostRepository hostRepository;
    private final VisitorRepository visitorRepository;

    public VisitLogServiceImpl(VisitLogRepository visitLogRepository,
                               HostRepository hostRepository,
                               VisitorRepository visitorRepository) {
        this.visitLogRepository = visitLogRepository;
        this.hostRepository = hostRepository;
        this.visitorRepository = visitorRepository;
    }

    @Override
    public VisitLogDTO checkInVisitor(VisitLogDTO dto) {
        Host host = hostRepository.findById(dto.getHostId())
                .orElseThrow(() -> new IllegalArgumentException("Host not found"));
        Visitor visitor = visitorRepository.findById(dto.getVisitorId())
                .orElseThrow(() -> new IllegalArgumentException("Visitor not found"));

        VisitLog log = new VisitLog();
        log.setHost(host);
        log.setVisitor(visitor);
        log.setCheckInTime(LocalDateTime.now());
        log.setCheckOutTime(null);

        VisitLog saved = visitLogRepository.save(log);
        return toDto(saved);
    }

    @Override
    public VisitLogDTO checkOutVisitor(Long id) {
        VisitLog log = visitLogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit log not found"));
        log.setCheckOutTime(LocalDateTime.now());
        VisitLog saved = visitLogRepository.save(log);
        return toDto(saved);
    }

    @Override
    public List<VisitLogDTO> getAllVisitLogs() {
        return visitLogRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public VisitLogDTO getVisitLogById(Long id) {
        VisitLog log = visitLogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit log not found"));
        return toDto(log);
    }

    @Override
    public List<VisitLogDTO> getActiveVisits() {
        return visitLogRepository.findByCheckOutTimeIsNull().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<VisitLogDTO> getVisitLogsByHostId(Long hostId) {
        return visitLogRepository.findByHostId(hostId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<VisitLogDTO> getVisitLogsByVisitorId(Long visitorId) {
        return visitLogRepository.findByVisitorId(visitorId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private VisitLogDTO toDto(VisitLog log) {
        VisitLogDTO dto = new VisitLogDTO();
        dto.setId(log.getId());
        dto.setHostId(log.getHost().getId());
        dto.setVisitorId(log.getVisitor().getId());
        dto.setCheckInTime(log.getCheckInTime());
        dto.setCheckOutTime(log.getCheckOutTime());
        return dto;
    }
}
