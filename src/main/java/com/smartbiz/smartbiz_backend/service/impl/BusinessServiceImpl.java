package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.BusinessResponse;
import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepo businessRepo;

    @Override
    public BusinessResponse findById(Long id) {
        Business business = businessRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        return BusinessResponse.builder()
                .id(business.getBusinessId())
                .name(business.getName())
                .address(business.getAddress())
                .phone(business.getPhone())
                .email(business.getEmail())
                .createdAt(business.getCreatedAt())
                .build();

    }

    @Override
    public List<BusinessResponse> getAll() {
        return businessRepo.findAll()
                .stream()
                .map(business -> BusinessResponse.builder()
                        .id(business.getBusinessId())
                        .name(business.getName())
                        .address(business.getAddress())
                        .phone(business.getPhone())
                        .email(business.getEmail())
                        .createdAt(business.getCreatedAt())
                        .build())
                .toList();
    }
}
