package com.example.demo.service;

import com.example.demo.dto.VisitLogDTO;
import java.util.List;

public interface VisitLogService {
    VisitLogDTO checkInVisitor(VisitLogDTO dto);
    VisitLogDTO checkOutVisitor(Long id);
    List<VisitLogDTO> getAllVisitLogs();
    VisitLogDTO getVisitLogById(Long id);
    List<VisitLogDTO> getActiveVisits();
    List<VisitLogDTO> getVisitLogsByHostId(Long hostId);
    List<VisitLogDTO> getVisitLogsByVisitorId(Long visitorId);
}
