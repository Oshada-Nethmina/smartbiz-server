package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfitReportResponse {
    private Double revenue;
    private Double expenses;
    private Double profit;
    private Double margin;
    private List<CategoryReport> chartData;
}
