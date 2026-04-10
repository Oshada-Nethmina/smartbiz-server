package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.AdminStateResponse;
import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.repository.AIRequestRepo;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.SubscriptionRepo;
import com.smartbiz.smartbiz_backend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BusinessRepo businessRepo;
    private final UserRepo userRepo;
    private final SubscriptionRepo subscriptionRepo;
    private final AIRequestRepo aiRequestRepo;


@GetMapping("/stats")
public ResponseEntity<ApiResponse<AdminStateResponse>> getStats(){
    LocalDateTime today = java.time.LocalDateTime.now().toLocalDate().atStartOfDay();
    return ResponseEntity.ok(ApiResponse.ok(AdminStateResponse.builder()
            .totalBusinesses(businessRepo.count())
            .totalUsers(userRepo.count())
            .activeSubscription(subscriptionRepo.findAll().stream().filter(s -> "ACTIVE".equals(s.getStatus())).count())
            .aiRequests(aiRequestRepo.countByCreatedAtAfter(today))
            .build()));
 }
}
