package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.SubscriptionRequest;
import com.smartbiz.smartbiz_backend.dto.SubscriptionResponse;
import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.entity.Subscription;
import com.smartbiz.smartbiz_backend.entity.SubscriptionPlan;
import com.smartbiz.smartbiz_backend.enums.SubscriptionStatus;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.SubscriptionPlanRepo;
import com.smartbiz.smartbiz_backend.repository.SubscriptionRepo;
import com.smartbiz.smartbiz_backend.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepo subscriptionRepo;
    private  final SubscriptionPlanRepo subscriptionPlanRepo;
    private final BusinessRepo businessRepo;
    @Override
    public SubscriptionResponse subscribe(SubscriptionRequest request) {
        Business business = businessRepo.findById(request.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        SubscriptionPlan plan = subscriptionPlanRepo.findById(request.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        Subscription subscription = subscriptionRepo
                .findByBusiness(business)
                .orElse(new Subscription());

        subscription.setBusiness(business);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setCreatedAt(LocalDateTime.now());

        subscriptionRepo.save(subscription);

        return toResponse(subscription);
    }

    @Override
    public SubscriptionResponse getBusinessSubscription(Long businessId) {

        Business business = businessRepo.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        return subscriptionRepo.findByBusiness(business)
                .map(this::toResponse)
                .orElse(null);
    }

    @Override
    public List<SubscriptionResponse> getAllSubscriptions() {
        return subscriptionRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public boolean cancelSubscription(Long businessId) {
        Business business = businessRepo.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Subscription subscription = subscriptionRepo.findByBusiness(business)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        subscription.setStatus(SubscriptionStatus.CANCELLED);

        subscriptionRepo.save(subscription);

        return true;
    }

    private SubscriptionResponse toResponse(Subscription subscription){

        SubscriptionPlan plan = subscription.getPlan();

        return SubscriptionResponse.builder()
                .subscriptionId(subscription.getSubscriptionId())
                .businessId(subscription.getBusiness().getBusinessId())
                .businessName(subscription.getBusiness().getName())
                .planId(plan.getPlanId())
                .planName(plan.getName())
                .price(plan.getPrice())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .status(subscription.getStatus())
                .aiEnabled(plan.getAiEnabled())
                .advancedReports(plan.getAdvancedReports())
                .emailSupport(plan.getEmailSupport())
                .prioritySupport(plan.getPrioritySupport())
                .maxUsers(plan.getMaxUsers())
                .maxProducts(plan.getMaxProducts())
                .build();
    }
}
