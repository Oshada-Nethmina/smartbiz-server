package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.AuthResponseDto;
import com.smartbiz.smartbiz_backend.dto.LoginRequestDto;
import com.smartbiz.smartbiz_backend.dto.RegisterRequestDto;
import com.smartbiz.smartbiz_backend.dto.UserResponseDto;
import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.entity.User;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.UserRepo;
import com.smartbiz.smartbiz_backend.security.JwtUtils;
import com.smartbiz.smartbiz_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final BusinessRepo businessRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponseDto login(LoginRequestDto loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
        String token = jwtUtils.generateToken(user.getEmail());
        return new AuthResponseDto(token, toUserResponse(user));
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto registerRequest) {
        if (userRepo.existsByEmail(registerRequest.getEmail())) throw new RuntimeException("Email already in use");
        Business business = businessRepo.save(Business.builder().name(registerRequest.getBusinessName()).address(registerRequest.getBusinessAddress()).build());
        User user = userRepo.save(User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
//                .phone(registerRequest.getPhone())
                .role("OWNER").business(business).build());
        return new AuthResponseDto(jwtUtils.generateToken(user.getEmail()), toUserResponse(user));
    }

    private UserResponseDto toUserResponse(User user) {
        return UserResponseDto.builder().id(user.getUserId()).name(user.getName()).email(user.getEmail())
                .role(user.getRole()).businessId(user.getBusiness() != null ? user.getBusiness().getBusinessId() : null)
                .businessName(user.getBusiness() != null ? user.getBusiness().getName() : null).build();
    }
}
