package com.smartbiz.smartbiz_backend.service;

public interface SubscriptionValidationService {
    void validateProductLimit(Long businessId);
    void validateUserLimit(Long businessId);
    void validateAI(Long businessId);
    void validateReports(Long businessId);
}
