package com.smartbiz.smartbiz_backend.service;

public interface OpenAIService {
    String complete (String systemPrompt, String userMessage);
}
