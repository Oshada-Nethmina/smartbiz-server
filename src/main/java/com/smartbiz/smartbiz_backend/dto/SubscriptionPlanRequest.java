package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlanRequest {
    private String name;
    private Double price;
    private Integer maxUsers;
    private Integer maxProducts;
    private Boolean aiEnabled;
    private Boolean advancedReports;
    private Boolean emailSupport;
    private Boolean prioritySupport;
    private Boolean active;
    private String description;

}
