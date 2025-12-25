package com.example.demo.service;

import com.example.demo.entity.Visitor;

public interface VisitorService {
    Visitor getVisitor(long id);
    Visitor createVisitor(Visitor visitor);
}
