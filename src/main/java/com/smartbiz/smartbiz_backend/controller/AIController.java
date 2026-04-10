package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.*;
import com.smartbiz.smartbiz_backend.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AIController {
    private final AIService aiService;

    @PostMapping("/insight")
    public ResponseEntity<ApiResponse<AIResponse>>businessInsight(@RequestBody AIInsightRequest insightRequest) {
        AIResponse business = aiService.businessInsight(insightRequest.getBusinessId(), insightRequest.getQuestion());
        return ResponseEntity.ok(ApiResponse.ok(business));
    }

    @PostMapping("/email")
    public ResponseEntity<ApiResponse<AIResponse>> generateEmail(@RequestBody AIEmailRequest emailRequest) {
        AIResponse email = aiService.generateEmail(emailRequest.getBusinessId(), emailRequest.getContext());
        return ResponseEntity.ok(ApiResponse.ok(email));
    }

    @PostMapping("/marketing")
    public ResponseEntity<ApiResponse<AIResponse>> generateMarketingPost(@RequestBody AIMarketingRequest marketingRequest) {
        AIResponse marketing = aiService.generateMarketingPost(marketingRequest.getBusinessId(), marketingRequest.getContext());
        return ResponseEntity.ok(ApiResponse.ok(marketing));
    }

    @PostMapping("/invoice-summary/{invoiceId}")
    public ResponseEntity<ApiResponse<AIResponse>> summarizeInvoice(@PathVariable Long invoiceId) {
        AIResponse summary = aiService.summarizeInvoice(invoiceId);
        return ResponseEntity.ok(ApiResponse.ok(summary));
    }
}
