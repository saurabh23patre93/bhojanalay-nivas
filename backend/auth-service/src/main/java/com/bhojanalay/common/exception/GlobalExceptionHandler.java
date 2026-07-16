package com.bhojanalay.common.exception;

import com.bhojanalay.auth.common.response.ApiError;
import com.bhojanalay.auth.common.response.FieldErrorResponse;
import com.bhojanalay.common.constants.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidation(
            MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error ->
                                FieldErrorResponse.builder()
                                        .field(error.getField())
                                        .message(error.getDefaultMessage())
                                        .build())
                        .toList();

        return ApiError.builder()
                .code(ErrorCode.VALIDATION_ERROR)
                .message("Validation Failed")
                .timestamp(LocalDateTime.now())
                .fieldErrors(errors)
                .build();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleEmail(
            EmailAlreadyExistsException ex){

        return ApiError.builder()
                .code(ErrorCode.EMAIL_ALREADY_EXISTS)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}