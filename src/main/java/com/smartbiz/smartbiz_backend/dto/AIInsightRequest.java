package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIInsightRequest {
    private Long businessId;
    private String question;
}
