package com.example.demo.service.impl;

import com.example.demo.entity.VisitLog;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;

@Service
public class VisitLogServiceImpl implements VisitLogService {

    private final VisitLogRepository repository;

    public VisitLogServiceImpl(VisitLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public VisitLog createVisitLog(VisitLog visitLog) {
        return repository.save(visitLog);
    }
}
