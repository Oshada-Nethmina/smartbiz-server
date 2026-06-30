package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.SubscriptionPlanRequest;
import com.smartbiz.smartbiz_backend.dto.SubscriptionPlanResponse;
import com.smartbiz.smartbiz_backend.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscription-plans")
@RequiredArgsConstructor
public class SubscriptionPlanController {
    private final SubscriptionPlanService subscriptionPlanService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<SubscriptionPlanResponse>> createPlan(
            @RequestBody SubscriptionPlanRequest request) {

        SubscriptionPlanResponse response =
                subscriptionPlanService.createPlan(request);

        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<SubscriptionPlanResponse>> updatePlan(
            @PathVariable Long id,
            @RequestBody SubscriptionPlanRequest request) {

        SubscriptionPlanResponse response =
                subscriptionPlanService.updatePlan(id, request);

        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {

        boolean deleted = subscriptionPlanService.deletePlan(id);

        if (deleted)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/toggle/{id}")
    public ResponseEntity<Void> toggleStatus(@PathVariable Long id) {

        boolean updated = subscriptionPlanService.toggleStatus(id);

        if (updated)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubscriptionPlanResponse>> getPlan(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.ok(subscriptionPlanService.getPlan(id))
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<SubscriptionPlanResponse>>> getAllPlans() {

        return ResponseEntity.ok(
                ApiResponse.ok(subscriptionPlanService.getAllPlans())
        );
    }
}
