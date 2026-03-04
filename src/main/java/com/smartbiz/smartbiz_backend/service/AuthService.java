package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.AuthResponseDto;
import com.smartbiz.smartbiz_backend.dto.LoginRequestDto;
import com.smartbiz.smartbiz_backend.dto.RegisterRequestDto;

public interface AuthService {
    AuthResponseDto login (LoginRequestDto loginRequest);
    AuthResponseDto register (RegisterRequestDto registerRequest);
}
