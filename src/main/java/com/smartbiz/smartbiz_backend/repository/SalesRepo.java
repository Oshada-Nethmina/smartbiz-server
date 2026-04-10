package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SalesRepo extends JpaRepository<Sales,Long> {
    List<Sales> findByBusinessBusinessId(Long businessId);
    List<Sales> findByBusinessBusinessIdOrderBySalesDateDesc(Long businessId);

    @Query("""
    SELECT SUM(s.totalAmount)
    FROM Sales s
    WHERE s.business.businessId = :businessId
    AND s.salesDate BETWEEN :start AND :end
""")
    Double sumTotalByBusinessIdAndDateBetween(
            @Param("businessId") Long businessId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
