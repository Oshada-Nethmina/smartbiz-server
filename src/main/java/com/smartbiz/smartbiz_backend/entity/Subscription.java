package com.smartbiz.smartbiz_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String planName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_business_id")
    private Business business;

    private LocalDateTime createdAt;

}
