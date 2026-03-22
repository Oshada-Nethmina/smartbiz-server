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
    private Double price;
    private Integer quantity;
    private String supplierName;
    private LocalDateTime createdAt;

    public ProductResponse(String name, String category, Integer quantity, Double cost, LocalDateTime createdAt, Long productId) {

    }


}
