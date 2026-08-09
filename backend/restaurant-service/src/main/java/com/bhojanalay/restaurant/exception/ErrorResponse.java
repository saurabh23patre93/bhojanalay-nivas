package com.bhojanalay.restaurant.exception;

import com.bhojanalay.restaurant.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private boolean success;

    private String message;

    private ErrorCode errorCode;

    private Map<String, String> errors;

    private LocalDateTime timestamp;
}