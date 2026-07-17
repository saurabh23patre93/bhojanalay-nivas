package com.bhojanalay.auth.service;

import com.bhojanalay.auth.entity.RefreshToken;
import com.bhojanalay.auth.entity.User;

import java.util.UUID;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);
    RefreshToken verifyRefreshToken(String token);
    void revokeToken(String token);
    void revokeAllForUser(UUID userId);
}
