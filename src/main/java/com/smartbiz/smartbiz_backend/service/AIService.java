package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.AIResponse;
import com.smartbiz.smartbiz_backend.entity.AIRequest;

public interface AIService {
    AIResponse businessInsight(Long businessId, String question);
    AIResponse generateEmail(Long businessId, String context);
    AIResponse generateMarketingPost(Long businessId, String context);
    AIResponse summarizeInvoice(Long invoiceId);
    String buildBusinessContext(Long businessId, String bizName);
    void saveAIRequest(Long businessId, String type, String prompt, String response);

}
