package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.SubscriptionPlanRequest;
import com.smartbiz.smartbiz_backend.dto.SubscriptionPlanResponse;
import com.smartbiz.smartbiz_backend.entity.SubscriptionPlan;
import com.smartbiz.smartbiz_backend.repository.SubscriptionPlanRepo;
import com.smartbiz.smartbiz_backend.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
    private final SubscriptionPlanRepo subscriptionPlanRepo;

    @Override
    public SubscriptionPlanResponse createPlan(SubscriptionPlanRequest request) {
        if(subscriptionPlanRepo.existsByName(request.getName())){
            throw new RuntimeException("Plan already exists.");
        }

        SubscriptionPlan plan = SubscriptionPlan.builder()
                .name(request.getName())
                .price(request.getPrice())
                .maxUsers(request.getMaxUsers())
                .maxProducts(request.getMaxProducts())
                .aiEnabled(request.getAiEnabled())
                .advancedReports(request.getAdvancedReports())
                .emailSupport(request.getEmailSupport())
                .prioritySupport(request.getPrioritySupport())
                .description(request.getDescription())
                .active(request.getActive())
                .createdAt(LocalDateTime.now())
                .build();

        subscriptionPlanRepo.save(plan);

        return toResponse(plan);
    }

    @Override
    public SubscriptionPlanResponse updatePlan(Long id, SubscriptionPlanRequest request) {
        SubscriptionPlan plan = subscriptionPlanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        plan.setName(request.getName());
        plan.setPrice(request.getPrice());
        plan.setMaxUsers(request.getMaxUsers());
        plan.setMaxProducts(request.getMaxProducts());
        plan.setAiEnabled(request.getAiEnabled());
        plan.setAdvancedReports(request.getAdvancedReports());
        plan.setEmailSupport(request.getEmailSupport());
        plan.setPrioritySupport(request.getPrioritySupport());
        plan.setDescription(request.getDescription());
        plan.setActive(request.getActive());

        subscriptionPlanRepo.save(plan);

        return toResponse(plan);
    }

    @Override
    public boolean deletePlan(Long id) {
        SubscriptionPlan plan = subscriptionPlanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        subscriptionPlanRepo.delete(plan);

        return true;
    }

    @Override
    public SubscriptionPlanResponse getPlan(Long id) {
        SubscriptionPlan plan = subscriptionPlanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        return toResponse(plan);
    }

    @Override
    public List<SubscriptionPlanResponse> getAllPlans() {

        return subscriptionPlanRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    @Override
    public boolean toggleStatus(Long id) {
        SubscriptionPlan plan = subscriptionPlanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        plan.setActive(!plan.getActive());

        subscriptionPlanRepo.save(plan);

        return true;
    }

    private SubscriptionPlanResponse toResponse(SubscriptionPlan plan){

        return SubscriptionPlanResponse.builder()
                .planId(plan.getPlanId())
                .name(plan.getName())
                .price(plan.getPrice())
                .maxUsers(plan.getMaxUsers())
                .maxProducts(plan.getMaxProducts())
                .aiEnabled(plan.getAiEnabled())
                .advancedReports(plan.getAdvancedReports())
                .emailSupport(plan.getEmailSupport())
                .prioritySupport(plan.getPrioritySupport())
                .active(plan.getActive())
                .description(plan.getDescription())
                .build();

    }

}
