package com.example.demo.service;
import com.example.demo.dto.VisitorDTO;
import java.util.List;

public interface VisitorService {
    VisitorDTO createVisitor(VisitorDTO visitorDTO);
    List<VisitorDTO> getAllVisitors();
    VisitorDTO getVisitorById(Long id);
    VisitorDTO updateVisitor(Long id, VisitorDTO visitorDTO);
    void deleteVisitor(Long id);
}
