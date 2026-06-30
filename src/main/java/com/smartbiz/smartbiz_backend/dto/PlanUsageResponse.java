package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanUsageResponse {
    private String plan;
    private Long totalBusinesses;
}
