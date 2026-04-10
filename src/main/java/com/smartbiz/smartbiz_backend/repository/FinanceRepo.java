package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FinanceRepo extends JpaRepository<Finance,Long> {
    @Query("""
    SELECT SUM(f.amount)
    FROM Finance f
    WHERE f.business.businessId = :businessId
    AND f.createdAt BETWEEN :start AND :end
""")
    Double sumByBusinessIdAndDateBetween(
            @Param("businessId") Long businessId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
