package com.example.demo.controller;

import com.example.demo.model.Visitor;
import com.example.demo.service.VisitorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
@Tag(name = "Visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public ResponseEntity<Visitor> create(@RequestBody Visitor visitor) {
        return new ResponseEntity<>(visitorService.createVisitor(visitor), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Visitor> getAll() {
        return visitorService.getAllVisitors();
    }

    @GetMapping("/{id}")
    public Visitor getOne(@PathVariable Long id) {
        return visitorService.getVisitor(id);
    }
}
