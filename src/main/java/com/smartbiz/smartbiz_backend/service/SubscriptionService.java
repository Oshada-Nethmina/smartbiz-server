package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.SubscriptionRequest;
import com.smartbiz.smartbiz_backend.dto.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {
    SubscriptionResponse subscribe(SubscriptionRequest request);
    SubscriptionResponse getBusinessSubscription(Long businessId);
    List<SubscriptionResponse> getAllSubscriptions();
    boolean cancelSubscription(Long businessId);
}
