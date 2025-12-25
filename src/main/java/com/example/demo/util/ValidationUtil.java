package com.example.demo.util;
import com.example.demo.exception.BadRequestException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^[0-9]{10}$";

    public static void validateEmail(String email) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new BadRequestException("Invalid email format");
        }
    }

    public static void validatePhone(String phone) {
        if (!Pattern.matches(PHONE_REGEX, phone)) {
            throw new BadRequestException("Phone number must be 10 digits");
        }
    }

    public static void validateAppointmentDate(LocalDate appointmentDate) {
        if (appointmentDate.isBefore(LocalDate.now())) {
            throw new BadRequestException("Appointment date cannot be in the past");
        }
    }

    public static void validateNotNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new BadRequestException(fieldName + " cannot be null");
        }
    }

    public static void validateNotEmpty(String str, String fieldName) {
        if (str == null || str.trim().isEmpty()) {
            throw new BadRequestException(fieldName + " cannot be empty");
        }
    }
}
