package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    private ResponseEntity<Object> buildResponse(
            HttpStatus status,
            String message,
            WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(body, status);
    }

    // 404 – resource not found (you can start using this later if you want)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    // 400 – validation / bad request
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(
            BadRequestException ex,
            WebRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    // 400 – bean validation errors (@NotBlank etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(err -> err.getField() + " " + err.getDefaultMessage())
                .orElse("Validation failed");
        return buildResponse(HttpStatus.BAD_REQUEST, message, request);
    }

    // 401 – wrong username/password
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(
            BadCredentialsException ex,
            WebRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password", request);
    }

    // 403 – access denied
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(
            AccessDeniedException ex,
            WebRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, "Access denied", request);
    }

    // 500 – fallback for any other unhandled exception (includes your current RuntimeExceptions)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(
            Exception ex,
            WebRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }
}
