package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesResponse {
    private Long id;
    private Long invoiceId;
    private String customerName;
    private String paymentMethod;
    private LocalDateTime salesDate;
    private Double totalAmount;
    private List<SalesItemResponse> items;
    private LocalDateTime createdAt;
}
