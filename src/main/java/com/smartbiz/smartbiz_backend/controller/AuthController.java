package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.AuthResponseDto;
import com.smartbiz.smartbiz_backend.dto.LoginRequestDto;
import com.smartbiz.smartbiz_backend.dto.RegisterRequestDto;
import com.smartbiz.smartbiz_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto login (@RequestBody LoginRequestDto loginRequestDto){
        AuthResponseDto authResponseDto = authService.login(loginRequestDto);
        return authResponseDto;
    }

    @PostMapping("/register")
    public AuthResponseDto register (@RequestBody RegisterRequestDto registerRequestDto){
        AuthResponseDto authResponseDto = authService.register(registerRequestDto);
        return authResponseDto;
    }


}
