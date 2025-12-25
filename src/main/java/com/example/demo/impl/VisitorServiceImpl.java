package com.example.demo.service.impl;

import com.example.demo.entity.Visitor;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitorService;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository repository;

    public VisitorServiceImpl(VisitorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Visitor getVisitor(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Visitor createVisitor(Visitor visitor) {
        return repository.save(visitor);
    }
}
