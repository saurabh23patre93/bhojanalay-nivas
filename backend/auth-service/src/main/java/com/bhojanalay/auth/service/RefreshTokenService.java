package com.bhojanalay.auth.service;

import com.bhojanalay.auth.entity.RefreshToken;
import com.bhojanalay.auth.entity.User;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);
    RefreshToken verifyRefreshToken(String token);
    void revokeToken(String token);
    void revokeAllForUser(Long userId);
}
