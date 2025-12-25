package com.example.demo.util;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {
    public static VisitLogDTO toVisitLogDTO(VisitLog visitLog) {
        VisitLogDTO dto = new VisitLogDTO();
        if (visitLog.getId() != null) dto.setId(visitLog.getId());
        if (visitLog.getVisitor() != null && visitLog.getVisitor().getId() != null) 
            dto.setVisitorId(visitLog.getVisitor().getId());
        if (visitLog.getHost() != null && visitLog.getHost().getId() != null) 
            dto.setHostId(visitLog.getHost().getId());
        dto.setCheckInTime(visitLog.getCheckInTime());
        dto.setCheckOutTime(visitLog.getCheckOutTime());
        dto.setPurpose(visitLog.getPurpose());
        dto.setAccessGranted(visitLog.isAccessGranted());
        dto.setAlertSent(visitLog.isAlertSent());
        return dto;
    }

    public static List<VisitLogDTO> toVisitLogDTOList(List<VisitLog> visitLogs) {
        return visitLogs.stream().map(DTOConverter::toVisitLogDTO).collect(Collectors.toList());
    }

    public static AppointmentDTO toAppointmentDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        if (appointment.getId() != null) dto.setId(appointment.getId());
        if (appointment.getVisitor() != null && appointment.getVisitor().getId() != null) 
            dto.setVisitorId(appointment.getVisitor().getId());
        if (appointment.getHost() != null && appointment.getHost().getId() != null) 
            dto.setHostId(appointment.getHost().getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setPurpose(appointment.getPurpose());
        dto.setStatus(appointment.getStatus());
        return dto;
    }

    public static List<AppointmentDTO> toAppointmentDTOList(List<Appointment> appointments) {
        return appointments.stream().map(DTOConverter::toAppointmentDTO).collect(Collectors.toList());
    }
}
