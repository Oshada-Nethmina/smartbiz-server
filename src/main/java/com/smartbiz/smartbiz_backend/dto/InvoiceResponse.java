package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private Long id;
    private Long salesId;
    private String customerName;
    private String customerEmail;
    private String paymentMethod;
    private Double totalAmount;
    private List<SalesItemResponse> items;
    private LocalDateTime createdAt;
}
