package com.example.demo.service;

import com.example.demo.entity.VisitLog;
import com.example.demo.dto.VisitLogDTO;
import java.util.List;

public interface VisitLogService {
    VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose);
    VisitLog checkOutVisitor(Long visitLogId);
    List<VisitLog> getAllVisitLogs();
    VisitLog getVisitLogById(Long id);
    List<VisitLog> getActiveVisits();
    List<VisitLog> getVisitLogsByHostId(Long hostId);
    List<VisitLog> getVisitLogsByVisitorId(Long visitorId);
}
