package com.example.demo.service;

import com.example.demo.model.Visitor;
import java.util.List;

public interface VisitorService {
    Visitor createVisitor(Visitor visitor);
    Visitor getVisitorById(Long id);
    Visitor getVisitor(long id); // added for test cases
    List<Visitor> getAllVisitors();
}







