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
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String Name;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "business_business_id")
    private Business business;

    private LocalDateTime createdAt;

}
