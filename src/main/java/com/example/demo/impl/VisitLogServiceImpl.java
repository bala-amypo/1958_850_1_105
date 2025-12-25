package com.example.demo.service.impl;
import com.example.demo.dto.VisitLogDTO;
import com.example.demo.entity.VisitLog;
import com.example.demo.entity.Visitor;
import com.example.demo.entity.Host;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitLogServiceImpl implements VisitLogService {
    @Autowired
    private VisitLogRepository visitLogRepository;

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private HostRepository hostRepository;

    @Override
    public VisitLogDTO checkInVisitor(VisitLogDTO visitLogDTO) {
        Visitor visitor = visitorRepository.findById(visitLogDTO.getVisitorId())
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        Host host = hostRepository.findById(visitLogDTO.getHostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host not found"));

        // Check if visitor already has active visit
        if (visitLogRepository.findByVisitorIdAndCheckOutTimeIsNull(visitLogDTO.getVisitorId()).isPresent()) {
            throw new BadRequestException("Visitor already has an active visit");
        }

        VisitLog visitLog = new VisitLog();
        visitLog.setVisitor(visitor);
        visitLog.setHost(host);
        visitLog.setPurpose(visitLogDTO.getPurpose());
        visitLog.setAccessGranted(visitLogDTO.getAccessGranted());
        visitLog.setAlertSent(false);

        VisitLog savedVisitLog = visitLogRepository.save(visitLog);
        return mapToDTO(savedVisitLog);
    }

    @Override
    public VisitLogDTO checkOutVisitor(Long visitLogId) {
        VisitLog visitLog = visitLogRepository.findById(visitLogId)
                .orElseThrow(() -> new ResourceNotFoundException("Visit log not found with id: " + visitLogId));

        if (visitLog.getCheckOutTime() != null) {
            throw new BadRequestException("Visitor already checked out");
        }

        visitLog.setCheckOutTime(LocalDateTime.now());
        VisitLog updatedVisitLog = visitLogRepository.save(visitLog);
        return mapToDTO(updatedVisitLog);
    }

    @Override
    public List<VisitLogDTO> getAllVisitLogs() {
        return visitLogRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitLogDTO getVisitLogById(Long id) {
        VisitLog visitLog = visitLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visit log not found with id: " + id));
        return mapToDTO(visitLog);
    }

    @Override
    public List<VisitLogDTO> getActiveVisits() {
        return visitLogRepository.findByCheckOutTimeIsNull().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitLogDTO> getVisitLogsByHostId(Long hostId) {
        return visitLogRepository.findByHostId(hostId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitLogDTO> getVisitLogsByVisitorId(Long visitorId) {
        return visitLogRepository.findByVisitorId(visitorId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private VisitLogDTO mapToDTO(VisitLog visitLog) {
        return new VisitLogDTO(visitLog.getId(), visitLog.getVisitor().getId(), visitLog.getHost().getId(),
                visitLog.getCheckInTime(), visitLog.getCheckOutTime(), visitLog.getPurpose(),
                visitLog.getAccessGranted(), visitLog.getAlertSent());
    }
}
