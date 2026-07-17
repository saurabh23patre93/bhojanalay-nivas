package com.bhojanalay.auth.service;

import com.bhojanalay.auth.dto.request.LoginRequest;
import com.bhojanalay.auth.dto.request.LogoutRequest;
import com.bhojanalay.auth.dto.request.RefreshTokenRequest;
import com.bhojanalay.auth.dto.request.RegisterRequest;
import com.bhojanalay.auth.dto.response.LoginResponse;
import com.bhojanalay.auth.dto.response.RefreshTokenResponse;
import com.bhojanalay.auth.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
    void logout(LogoutRequest request);
}