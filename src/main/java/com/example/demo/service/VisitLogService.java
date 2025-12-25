package com.example.demo.service;

import com.example.demo.model.VisitLog;
import java.util.List;

public interface VisitLogService {
    VisitLog checkInVisitor(Long visitorId, Long hostId, String purpose);
    VisitLog checkOutVisitor(Long visitLogId);
    VisitLog getVisitLog(long id); // for test cases
    List<VisitLog> getActiveVisits();
}