package com.bhojanalay.auth.common.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ApiError {

    private String code;

    private String message;

    private LocalDateTime timestamp;

    private List<FieldErrorResponse> fieldErrors;
}