package com.example.demo.service;

import com.example.demo.dto.VisitLogDTO;
import java.util.List;

public interface VisitLogService {

    VisitLogDTO getVisitLog(long id);

    List<VisitLogDTO> getVisitLogsForVisitor(long visitorId);

    List<VisitLogDTO> getVisitLogsForHost(long hostId);
}
