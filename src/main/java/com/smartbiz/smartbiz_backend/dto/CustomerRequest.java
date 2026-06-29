package com.smartbiz.smartbiz_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
    @NotBlank private String name;
    @Email @NotBlank private String email;
    private String phoneNumber;
    private String address;
    @NotNull private Long businessId;
}
