package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.FinanceRequest;
import com.smartbiz.smartbiz_backend.dto.FinanceResponse;

import java.util.List;

public interface FinanceService {
    FinanceResponse saveFinance (FinanceRequest financeRequest);
    FinanceResponse searchFinance (Long financeId);
    boolean updateFinance (Long financeId,FinanceRequest financeRequest);
    boolean deleteFinance (Long id);
    List<FinanceResponse> getAllFinances();
}
