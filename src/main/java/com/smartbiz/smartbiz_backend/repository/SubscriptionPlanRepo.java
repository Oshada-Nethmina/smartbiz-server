package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionPlanRepo extends JpaRepository<SubscriptionPlan, Long> {
    Optional<SubscriptionPlan> findByName(String name);
    boolean existsByName(String name);
}
