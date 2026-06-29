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
public class ExpenseSummaryResponse {
    private Double totalExpenses;
    private Double change;
    private List<ChartDataPoint> chartData;
}
