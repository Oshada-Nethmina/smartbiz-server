package com.smartbiz.smartbiz_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminStateResponse {
    private long totalBusinesses;
    private long totalUsers;
    private long activeSubscription;
    private long aiRequests;
}
