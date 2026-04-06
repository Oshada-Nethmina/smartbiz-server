package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesItemRequest {
    private Long productId;
    private Integer quantity;
    private Double price;
    private Double subtotal;
}
