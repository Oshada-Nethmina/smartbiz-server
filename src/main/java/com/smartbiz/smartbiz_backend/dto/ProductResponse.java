package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String category;
    private Double cost;
    private Integer quantity;
    private String supplierName;
    private LocalDateTime createdAt;

    public ProductResponse(String name, String category, Integer quantity, Double cost, LocalDateTime createdAt, Long productId) {

    }


    public ProductResponse(Long productId, String name, String category, Double cost, Integer quantity, Long businessId, Long aLong) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.cost = cost;
        this.id = productId;
    }
}
