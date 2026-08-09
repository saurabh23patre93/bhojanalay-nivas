package com.bhojanalay.restaurant.exception;

import com.bhojanalay.restaurant.enums.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {

        ErrorResponse response = ErrorResponse.builder().success(false).message(exception.getMessage()).errorCode(exception.getErrorCode()).timestamp(LocalDateTime.now()).build();
        HttpStatus status;
        if (exception.getErrorCode() == ErrorCode.RESOURCE_NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception.getErrorCode() == ErrorCode.DUPLICATE_RESOURCE) {
            status = HttpStatus.CONFLICT;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ErrorResponse response = ErrorResponse.builder().success(false).message("Validation failed").errorCode(ErrorCode.VALIDATION_ERROR).errors(errors).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException exception) {
        ErrorResponse response = ErrorResponse.builder().success(false).message("Validation failed").errorCode(ErrorCode.VALIDATION_ERROR).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
        ErrorResponse response = ErrorResponse.builder().success(false).message("An unexpected error occurred").errorCode(ErrorCode.INTERNAL_SERVER_ERROR).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}