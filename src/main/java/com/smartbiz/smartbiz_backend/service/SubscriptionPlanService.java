package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.SubscriptionPlanRequest;
import com.smartbiz.smartbiz_backend.dto.SubscriptionPlanResponse;

import java.util.List;

public interface SubscriptionPlanService {
    SubscriptionPlanResponse createPlan(SubscriptionPlanRequest request);
    SubscriptionPlanResponse updatePlan(Long id, SubscriptionPlanRequest request);
    boolean deletePlan(Long id);
    SubscriptionPlanResponse getPlan(Long id);
    List<SubscriptionPlanResponse> getAllPlans();
    boolean toggleStatus(Long id);
}
