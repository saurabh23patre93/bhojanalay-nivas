package com.bhojanalay.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class RegisterResponse {

    private UUID userId;

    private String message;
}