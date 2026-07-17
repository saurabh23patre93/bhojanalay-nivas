package com.bhojanalay.common.exception;

public class RefreshTokenNotFoundException extends RuntimeException {
    public RefreshTokenNotFoundException(String token) {
        super("Refresh token not found: " + token);
    }
}