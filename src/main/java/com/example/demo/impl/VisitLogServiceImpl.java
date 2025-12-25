package com.example.demo.service.impl;

import com.example.demo.dto.VisitLogDTO;
import com.example.demo.entity.VisitLog;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitLogServiceImpl implements VisitLogService {

    private final VisitLogRepository visitLogRepository;

    public VisitLogServiceImpl(VisitLogRepository visitLogRepository) {
        this.visitLogRepository = visitLogRepository;
    }

    @Override
    public VisitLogDTO getVisitLog(long id) {
        VisitLog log = visitLogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit log not found"));
        return toDto(log);
    }

    @Override
    public List<VisitLogDTO> getVisitLogsForVisitor(long visitorId) {
        return visitLogRepository.findByVisitorId(visitorId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitLogDTO> getVisitLogsForHost(long hostId) {
        return visitLogRepository.findByHostId(hostId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private VisitLogDTO toDto(VisitLog log) {
        VisitLogDTO dto = new VisitLogDTO();
        dto.setId(log.getId());
        dto.setVisitorId(log.getVisitor().getId());
        dto.setHostId(log.getHost().getId());
        dto.setCheckInTime(log.getCheckInTime());
        dto.setCheckOutTime(log.getCheckOutTime());
        dto.setRemarks(log.getRemarks());
        return dto;
    }
}
