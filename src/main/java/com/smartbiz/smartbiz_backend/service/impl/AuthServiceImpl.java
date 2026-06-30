package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.AuthResponseDto;
import com.smartbiz.smartbiz_backend.dto.LoginRequestDto;
import com.smartbiz.smartbiz_backend.dto.RegisterRequestDto;
import com.smartbiz.smartbiz_backend.dto.UserResponseDto;
import com.smartbiz.smartbiz_backend.entity.*;
import com.smartbiz.smartbiz_backend.enums.SubscriptionStatus;
import com.smartbiz.smartbiz_backend.repository.*;
import com.smartbiz.smartbiz_backend.security.JwtUtils;
import com.smartbiz.smartbiz_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final AdminRepo adminRepo;
    private final BusinessRepo businessRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final SubscriptionRepo subscriptionRepo;
    private final SubscriptionPlanRepo subscriptionPlanRepo;


    @Override
    public AuthResponseDto login(LoginRequestDto loginRequest) {
        var adminOpt = adminRepo.findByEmail(loginRequest.getEmail());
        var userOpt = userRepo.findByEmail(loginRequest.getEmail());

        if (adminOpt.isPresent() && userOpt.isPresent()) {
            throw new RuntimeException("Duplicate email detected. Contact system administrator.");
        }

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();

            if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
                throw new RuntimeException("Invalid credentials");
            }

            String token = jwtUtils.generateToken(admin.getEmail());
            return new AuthResponseDto(token, toAdminResponse(admin));
        }

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid credentials");
            }

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));

            String token = jwtUtils.generateToken(user.getEmail());
            return new AuthResponseDto(token, toUserResponse(user));
        }

        throw new RuntimeException("Invalid credentials");
    }

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto registerRequest) {
        if (adminRepo.existsByEmail(registerRequest.getEmail())
                || userRepo.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        String role = registerRequest.getRole() != null
                ? registerRequest.getRole().toUpperCase()
                : "OWNER";

        if ("ADMIN".equals(role)) {

            Admin admin = adminRepo.save(Admin.builder()
                    .name(registerRequest.getName())
                    .email(registerRequest.getEmail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .phone(registerRequest.getPhone())
                    .build());

            String token = jwtUtils.generateToken(admin.getEmail());
            return new AuthResponseDto(token, toAdminResponse(admin));
        }

        Business business = businessRepo.save(Business.builder()
                .name(registerRequest.getBusinessName())
                .address(registerRequest.getBusinessAddress())
                .build());

        SubscriptionPlan freePlan = subscriptionPlanRepo
                .findByName("Free")
                .orElseThrow(() ->
                        new RuntimeException("Free subscription plan not found"));

        subscriptionRepo.save(
                Subscription.builder()
                        .business(business)
                        .plan(freePlan)
                        .status(SubscriptionStatus.ACTIVE)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusMonths(1))
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        User user = userRepo.save(User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role("OWNER")
                .business(business)
                .build());

        String token = jwtUtils.generateToken(user.getEmail());
        return new AuthResponseDto(token, toUserResponse(user));
    }

    private UserResponseDto toUserResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .businessId(user.getBusiness() != null ? user.getBusiness().getBusinessId() : null)
                .businessName(user.getBusiness() != null ? user.getBusiness().getName() : null)
                .build();
    }

    private UserResponseDto toAdminResponse(Admin admin) {
        return UserResponseDto.builder()
                .id(admin.getAdminId())
                .name(admin.getName())
                .email(admin.getEmail())
                .role("ADMIN")
                .businessId(null)
                .businessName(null)
                .build();
    }

}
