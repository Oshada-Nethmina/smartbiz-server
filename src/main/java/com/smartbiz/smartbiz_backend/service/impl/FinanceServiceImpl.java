package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.FinanceRequest;
import com.smartbiz.smartbiz_backend.dto.FinanceResponse;
import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.entity.Finance;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.FinanceRepo;
import com.smartbiz.smartbiz_backend.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepo financeRepo;
    private final BusinessRepo businessRepo;

    @Override
    public FinanceResponse saveFinance(FinanceRequest financeRequest) {
        Business business = businessRepo.findById(financeRequest.getBusinessId()).orElseThrow();
        Finance save = Finance.builder()
                .amount(financeRequest.getAmount())
                .category(financeRequest.getCategory())
                .title(financeRequest.getTitle())
                .createdAt(Optional.ofNullable(financeRequest.getDate()).orElse(LocalDateTime.now()))
                .business(business) // ✅ now it's valid
                .build();

        financeRepo.save(save);

        return toResponseFinance(save);
    }

    @Override
    public FinanceResponse searchFinance(Long financeId) {
        Finance search = financeRepo.findById(financeId).orElseThrow(() -> new RuntimeException("Finance not found"));
        return toResponseFinance(search);
    }

    @Override
    public boolean updateFinance(Long financeId, FinanceRequest financeRequest) {
        Finance update = financeRepo.findById(financeId).orElseThrow(() -> new RuntimeException("Business not found"));
        if (financeRequest.getAmount() != null) {
            update.setAmount(financeRequest.getAmount());
            update.setTitle(financeRequest.getTitle());
            update.setCategory(financeRequest.getCategory());
            update.setBusiness(businessRepo.findById(financeRequest.getBusinessId()).orElseThrow(() -> new RuntimeException("Business not found")));
            update.setCreatedAt(LocalDateTime.now());
            financeRepo.save(update);
        }
        return true;
    }

    @Override
    public boolean deleteFinance(Long id) {
        Finance delete = financeRepo.findById(id).orElseThrow(() -> new RuntimeException("Business not found"));
        financeRepo.delete(delete);
        return false;
    }

    @Override
    public List<FinanceResponse> getAllFinances() {
        List<Finance> allFinances = financeRepo.findAll();
        return allFinances.stream()
                .map(this::toResponseFinance)
                .toList();
    }

    public FinanceResponse toResponseFinance (Finance finance) {
        return FinanceResponse.builder().id(finance.getFinanceId()).amount(finance.getAmount())
                .category(finance.getCategory()).date(finance.getCreatedAt()).build();
    }
}
