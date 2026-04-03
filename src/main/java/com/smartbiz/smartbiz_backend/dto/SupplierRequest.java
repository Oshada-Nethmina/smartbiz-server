package com.smartbiz.smartbiz_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {
    @NotBlank
    private String name;
    private String email;
    private String phone;
    private Long businessId;
}
