package com.smartbiz.smartbiz_backend.config;

import com.smartbiz.smartbiz_backend.entity.SubscriptionPlan;
import com.smartbiz.smartbiz_backend.repository.SubscriptionPlanRepo;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SubscriptionDataSeeder implements CommandLineRunner {
    private final SubscriptionPlanRepo subscriptionPlanRepo;
    @Override
    public void run(String @NonNull ... args) throws Exception {
        if(subscriptionPlanRepo.count()>0)
            return;

        subscriptionPlanRepo.save(
                SubscriptionPlan.builder()
                        .name("Free")
                        .price(0.0)
                        .maxUsers(1)
                        .maxProducts(50)
                        .aiEnabled(false)
                        .advancedReports(false)
                        .emailSupport(false)
                        .prioritySupport(false)
                        .active(true)
                        .description("Free Plan")
                        .createdAt(LocalDateTime.now())
                        .build());

        subscriptionPlanRepo.save(
                SubscriptionPlan.builder()
                        .name("Starter")
                        .price(990.0)
                        .maxUsers(5)
                        .maxProducts(500)
                        .aiEnabled(false)
                        .advancedReports(false)
                        .emailSupport(true)
                        .prioritySupport(false)
                        .active(true)
                        .description("Starter Plan")
                        .createdAt(LocalDateTime.now())
                        .build());

        subscriptionPlanRepo.save(
                SubscriptionPlan.builder()
                        .name("Pro")
                        .price(2490.0)
                        .maxUsers(20)
                        .maxProducts(99999)
                        .aiEnabled(true)
                        .advancedReports(true)
                        .emailSupport(true)
                        .prioritySupport(false)
                        .active(true)
                        .description("Professional Plan")
                        .createdAt(LocalDateTime.now())
                        .build());

        subscriptionPlanRepo.save(
                SubscriptionPlan.builder()
                        .name("Enterprise")
                        .price(4990.0)
                        .maxUsers(999)
                        .maxProducts(999999)
                        .aiEnabled(true)
                        .advancedReports(true)
                        .emailSupport(true)
                        .prioritySupport(true)
                        .active(true)
                        .description("Enterprise Plan")
                        .createdAt(LocalDateTime.now())
                        .build());

        System.out.println("Subscription Plans Seeded");

    }
}
