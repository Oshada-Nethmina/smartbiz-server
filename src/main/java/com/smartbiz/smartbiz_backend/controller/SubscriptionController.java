package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.SubscriptionRequest;
import com.smartbiz.smartbiz_backend.dto.SubscriptionResponse;
import com.smartbiz.smartbiz_backend.dto.SubscriptionStatisticsResponse;
import com.smartbiz.smartbiz_backend.service.SubscriptionService;
import com.smartbiz.smartbiz_backend.service.SubscriptionStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionStatisticsService subscriptionStatisticsService;

    @PostMapping("/subscribe")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> subscribe(
            @RequestBody SubscriptionRequest request) {

        SubscriptionResponse response =
                subscriptionService.subscribe(request);

        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<ApiResponse<SubscriptionResponse>>
    getBusinessSubscription(@PathVariable Long businessId) {

        return ResponseEntity.ok(
                ApiResponse.ok(
                        subscriptionService.getBusinessSubscription(businessId)
                )
        );
    }

    @DeleteMapping("/cancel/{businessId}")
    public ResponseEntity<Void> cancelSubscription(
            @PathVariable Long businessId) {

        boolean cancelled =
                subscriptionService.cancelSubscription(businessId);

        if (cancelled)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<SubscriptionResponse>>>
    getAllSubscriptions() {

        return ResponseEntity.ok(
                ApiResponse.ok(subscriptionService.getAllSubscriptions())
        );
    }

    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<SubscriptionStatisticsResponse>>
    getStatistics() {

        return ResponseEntity.ok(
                ApiResponse.ok(
                        subscriptionStatisticsService.getStatistics()
                )
        );


    }
}