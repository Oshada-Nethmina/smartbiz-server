package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionStatisticsResponse {
    private Long totalPlans;
    private Long activeSubscriptions;
    private Long expiredSubscriptions;
    private Double monthlyRevenue;
    private List<PlanUsageResponse> planUsage;
}
