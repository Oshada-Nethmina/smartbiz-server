package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.PlanUsageResponse;
import com.smartbiz.smartbiz_backend.dto.SubscriptionStatisticsResponse;
import com.smartbiz.smartbiz_backend.enums.SubscriptionStatus;
import com.smartbiz.smartbiz_backend.repository.SubscriptionPlanRepo;
import com.smartbiz.smartbiz_backend.repository.SubscriptionRepo;
import com.smartbiz.smartbiz_backend.service.SubscriptionStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionStatisticsServiceImpl implements SubscriptionStatisticsService {
    private final SubscriptionRepo subscriptionRepo;
    private final SubscriptionPlanRepo subscriptionPlanRepo;

    @Override
    public SubscriptionStatisticsResponse getStatistics() {
        List<PlanUsageResponse> usage =
                subscriptionRepo.getPlanUsage()
                        .stream()
                        .map(obj -> PlanUsageResponse.builder()
                                .plan((String) obj[0])
                                .totalBusinesses((Long) obj[1])
                                .build())
                        .toList();

        return SubscriptionStatisticsResponse.builder()
                .totalPlans(subscriptionPlanRepo.count())
                .activeSubscriptions(
                        subscriptionRepo.countByStatus(
                                SubscriptionStatus.ACTIVE))
                .expiredSubscriptions(
                        subscriptionRepo.countByStatus(
                                SubscriptionStatus.EXPIRED))
                .monthlyRevenue(
                        subscriptionRepo.calculateMonthlyRevenue() == null
                                ? 0.0
                                : subscriptionRepo.calculateMonthlyRevenue())
                .planUsage(usage)
                .build();
    }
}
