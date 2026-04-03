package com.smartbiz.smartbiz_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String name;
    private String phone;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "businessId")
    private Business business;

    @CreationTimestamp private LocalDateTime createdAt;

    public Supplier(@NotBlank String name, String email, String phone, Business business) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.business = business;
    }
}
