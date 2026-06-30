package com.smartbiz.smartbiz_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscription_plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer maxUsers;

    @Column(nullable = false)
    private Integer maxProducts;

    @Column(nullable =false)
    private Boolean aiEnabled;

    @Column(nullable = false)
    private Boolean advancedReports;

    @Column(nullable = false)
    private Boolean emailSupport;

    @Column(nullable = false)
    private Boolean prioritySupport;

    @Column(nullable = false)
    private Boolean active;

    @Column(length = 1000)
    private String description;

    private LocalDateTime createdAt;
}
