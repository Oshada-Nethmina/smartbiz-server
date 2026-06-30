package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.entity.Subscription;
import com.smartbiz.smartbiz_backend.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepo extends JpaRepository<Subscription,Long> {
    Optional<Subscription> findByBusiness(Business business);
    Optional<Subscription> findByBusinessAndStatus(
            Business business,
            SubscriptionStatus status
    );
    List<Subscription> findByStatus(SubscriptionStatus status);
    long countByStatus(SubscriptionStatus status);
    // Expired subscriptions
    List<Subscription> findByEndDateBefore(LocalDate date);

    // Monthly Revenue
    @Query("""
            SELECT COALESCE(SUM(s.plan.price),0)
            FROM Subscription s
            WHERE s.status = com.smartbiz.smartbiz_backend.enums.SubscriptionStatus.ACTIVE
            """)
    Double calculateMonthlyRevenue();

    // Plan Usage
    @Query("""
            SELECT s.plan.name, COUNT(s)
            FROM Subscription s
            GROUP BY s.plan.name
            """)
    List<Object[]> getPlanUsage();


}
