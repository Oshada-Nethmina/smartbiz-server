package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesRequest {
    private Long customerId;
    private String paymentMethod;
    private LocalDateTime salesDate;
    private Double totalAmount;
    private Long businessId;
    private Long userId;
    private List<SalesItemRequest> items;
}
