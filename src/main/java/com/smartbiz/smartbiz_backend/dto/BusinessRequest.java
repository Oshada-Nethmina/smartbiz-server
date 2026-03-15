package com.smartbiz.smartbiz_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessRequest {
    @NotBlank
    private String name;
    private String address;
    private String phone;
    private String email;
}
