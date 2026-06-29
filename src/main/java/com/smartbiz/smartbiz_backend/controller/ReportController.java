package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.ProfitReportResponse;
import com.smartbiz.smartbiz_backend.dto.ReportResponse;
import com.smartbiz.smartbiz_backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/sales")
    public ResponseEntity<ApiResponse<ReportResponse>> salesReport(
            @RequestParam Long businessId,
            @RequestParam String period) {

        return ResponseEntity.ok(
                ApiResponse.ok(reportService.getSalesReport(businessId, period))
        );
    }

    @GetMapping("/profit")
    public ResponseEntity<ApiResponse<ProfitReportResponse>> profitReport(
            @RequestParam Long businessId,
            @RequestParam String period) {

        return ResponseEntity.ok(
                ApiResponse.ok(reportService.getProfitReport(businessId, period))
        );
    }
}
