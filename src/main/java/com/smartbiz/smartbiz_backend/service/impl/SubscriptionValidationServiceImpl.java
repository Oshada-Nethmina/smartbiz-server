package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.entity.Subscription;
import com.smartbiz.smartbiz_backend.enums.SubscriptionStatus;
import com.smartbiz.smartbiz_backend.exception.SubscriptionException;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.ProductRepo;
import com.smartbiz.smartbiz_backend.repository.SubscriptionRepo;
import com.smartbiz.smartbiz_backend.repository.UserRepo;
import com.smartbiz.smartbiz_backend.service.SubscriptionValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubscriptionValidationServiceImpl implements SubscriptionValidationService {
    private final SubscriptionRepo subscriptionRepo;
    private final BusinessRepo businessRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    private Subscription getSubscription(Long businessId){

        Business business = businessRepo.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        return subscriptionRepo
                .findByBusinessAndStatus(
                        business,
                        SubscriptionStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new SubscriptionException(
                                "No active subscription found."
                        ));
    }

    @Override
    public void validateProductLimit(Long businessId) {

        Subscription subscription = getSubscription(businessId);

        long currentProducts =
                productRepo.countByBusinessBusinessId(businessId);

        if(currentProducts >=
                subscription.getPlan().getMaxProducts()){
            throw new SubscriptionException(
                    "Product limit exceeded. Upgrade your subscription."
            );
        }
    }

    @Override
    public void validateUserLimit(Long businessId) {
        Subscription subscription = getSubscription(businessId);

        long users =
                userRepo.countByBusinessBusinessId(businessId);

        if(users >=
                subscription.getPlan().getMaxUsers()){

            throw new SubscriptionException(
                    "Maximum users reached."
            );
        }

    }

    @Override
    public void validateAI(Long businessId) {
        Subscription subscription = getSubscription(businessId);

        if(!subscription.getPlan().getAiEnabled()){

            throw new SubscriptionException(
                    "AI is available only for Pro and Enterprise plans."
            );

        }
    }

    @Override
    public void validateReports(Long businessId) {
        Subscription subscription = getSubscription(businessId);

        if(!subscription.getPlan().getAdvancedReports()){

            throw new SubscriptionException(
                    "Advanced reports require a premium subscription."
            );

        }

    }
}
