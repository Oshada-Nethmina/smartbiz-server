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
public class SalesSummaryResponse {
    private Double totalRevenue;
    private Double change;
    private List<ChartDataPoint> chartData;
    private List<SalesResponse> recentSales;
}
