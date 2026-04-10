package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.AIResponse;
import com.smartbiz.smartbiz_backend.entity.AIRequest;
import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.repository.AIRequestRepo;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.FinanceRepo;
import com.smartbiz.smartbiz_backend.repository.SalesRepo;
import com.smartbiz.smartbiz_backend.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {
    private final OpenAIServiceImpl openAIService;
    private final SalesRepo salesRepo;
    private final FinanceRepo financeRepo;
    private final AIRequestRepo aiRequestRepo;
    private final BusinessRepo businessRepo;

    @Override
    public AIResponse businessInsight(Long businessId, String question) {
        Business business = businessRepo.findById(businessId).orElseThrow();
        String context = buildBusinessContext(businessId, business.getName());
        String answer = openAIService.complete(
                "You are a helpful business analyst AI for SmartBiz. Answer questions about the business based on the context. Be concise and actionable.",
                "Business Context:\n" + context + "\n\nQuestion: " + question
        );
        saveAIRequest(businessId, "INSIGHT", question, answer);
        return AIResponse.builder().result(answer).type("INSIGHT").generatedAt(LocalDateTime.now()).build();
    }

    @Override
    public AIResponse generateEmail(Long businessId, String context) {
        String result = openAIService.complete(
                "You are a professional email writer for a business. Write a clear, professional email based on the given context.",
                context
        );
        saveAIRequest(businessId, "EMAIL", context, result);
        return AIResponse.builder().result(result).type("EMAIL").generatedAt(LocalDateTime.now()).build();
    }

    @Override
    public AIResponse generateMarketingPost(Long businessId, String context) {
        String result = openAIService.complete(
                "You are a creative social media content writer. Generate engaging marketing content for a small business.",
                context
        );
        saveAIRequest(businessId, "MARKETING", context, result);
        return AIResponse.builder().result(result).type("MARKETING").generatedAt(LocalDateTime.now()).build();
    }

    @Override
    public AIResponse summarizeInvoice(Long invoiceId) {
        String result = openAIService.complete(
                "You are a helpful assistant. Summarize this invoice in simple, clear terms for a business owner.",
                "Invoice ID: " + invoiceId + ". Please provide a brief, plain-English summary of what this invoice represents."
        );
        return AIResponse.builder().result(result).type("INVOICE_SUMMARY").generatedAt(LocalDateTime.now()).build();
    }

    @Override
    public String buildBusinessContext(Long businessId, String bizName) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.minusMonths(1);
        Double revenue = salesRepo.sumTotalByBusinessIdAndDateBetween(businessId, start, now);
        Double expenses = financeRepo.sumByBusinessIdAndDateBetween(businessId, start, now);
        return String.format("Business: %s\nRevenue (last 30 days): LKR %.2f\nExpenses (last 30 days): LKR %.2f\nNet Profit: LKR %.2f",
                bizName, revenue != null ? revenue : 0, expenses != null ? expenses : 0,
                (revenue != null ? revenue : 0) - (expenses != null ? expenses : 0));
    }

    @Override
    public void saveAIRequest(Long businessId, String type, String prompt, String response) {
        try {
            Business business = businessRepo.findById(businessId).orElseThrow();
            aiRequestRepo.save(AIRequest.builder().type(type).prompt(prompt).response(response)
                    .business(business).date(LocalDateTime.now()).build());
        } catch (Exception ignored) {
        }
    }
}



