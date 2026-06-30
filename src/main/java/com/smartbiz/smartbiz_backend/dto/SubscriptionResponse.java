package com.smartbiz.smartbiz_backend.dto;

import com.smartbiz.smartbiz_backend.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionResponse {
    private Long subscriptionId;
    private Long businessId;
    private String businessName;
    private Long planId;
    private String planName;
    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private SubscriptionStatus status;
    private Boolean aiEnabled;
    private Boolean advancedReports;
    private Boolean emailSupport;
    private Boolean prioritySupport;
    private Integer maxUsers;
    private Integer maxProducts;
}
