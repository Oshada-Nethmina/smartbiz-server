package com.smartbiz.smartbiz_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank private String name;
    private String category;
    private Double cost;
    private Integer quantity;
    private Long businessId;
    private Long supplierId;
}
