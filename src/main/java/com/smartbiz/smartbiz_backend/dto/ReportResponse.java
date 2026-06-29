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

public class ReportResponse {
    private Double totalSales;
    private Double totalExpenses;
    private Double netProfit;

    private List<ChartDataPoint> chartData;
}
