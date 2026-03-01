package com.smartbiz.smartbiz_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AIRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    private String type;      // INSIGHT, EMAIL, MARKETING, INVOICE_SUMMARY
    private String prompt;
    @Column(columnDefinition = "TEXT")
    private String response;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "business_business_id")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

    private LocalDateTime createdAt;

}
