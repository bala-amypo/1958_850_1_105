package com.example.demo.controller;

import com.example.demo.entity.VisitLog;
import com.example.demo.service.VisitLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitlogs")
public class VisitLogController {

    private final VisitLogService service;

    public VisitLogController(VisitLogService service) {
        this.service = service;
    }

    @PostMapping
    public VisitLog createVisitLog(@RequestBody VisitLog visitLog) {
        return service.createVisitLog(visitLog);
    }
}
