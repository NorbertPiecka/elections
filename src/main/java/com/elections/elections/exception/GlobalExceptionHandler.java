package com.elections.elections.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Map<String, Object>> handleErrors(RuntimeException exception) {
        HttpStatus status;
        if (exception instanceof IllegalArgumentException) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        Map<String, Object> errorResponse = Map.of(
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", exception.getMessage(),
                "timestamp", LocalDateTime.now().toString()
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
