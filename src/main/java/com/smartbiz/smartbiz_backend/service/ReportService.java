package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.ProfitReportResponse;
import com.smartbiz.smartbiz_backend.dto.ReportResponse;

public interface ReportService {
    ReportResponse getSalesReport(Long businessId, String period);
    ProfitReportResponse getProfitReport(Long businessId, String period);
}
