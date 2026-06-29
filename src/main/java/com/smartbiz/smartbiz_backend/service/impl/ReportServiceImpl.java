package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.ChartDataPoint;
import com.smartbiz.smartbiz_backend.dto.ProfitReportResponse;
import com.smartbiz.smartbiz_backend.dto.ReportResponse;
import com.smartbiz.smartbiz_backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Override
    public ReportResponse getSalesReport(Long businessId, String period) {
        return ReportResponse.builder()
                .totalSales(0.0)
                .totalExpenses(0.0)
                .netProfit(0.0)
                .chartData(new ArrayList<>())
                .build();
    }

    @Override
    public ProfitReportResponse getProfitReport(Long businessId, String period) {
        return ProfitReportResponse.builder()
                .revenue(0.0)
                .expenses(0.0)
                .profit(0.0)
                .margin(0.0)
                .build();

    }
}
