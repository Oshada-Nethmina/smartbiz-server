package com.smartbiz.smartbiz_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceRequest {
    @NotNull
    private Double amount;
    private String title;
    private String category;
    private LocalDateTime date;
    private String description;
    private Long businessId;
    private Long userId;
}
