package com.bhojanalay.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest {

    @NotBlank
    private String refreshToken;

}