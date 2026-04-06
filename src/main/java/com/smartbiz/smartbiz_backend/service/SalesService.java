package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.SalesRequest;
import com.smartbiz.smartbiz_backend.dto.SalesResponse;

import java.util.List;

public interface SalesService {
    SalesResponse saveSales (SalesRequest salesRequest);
    SalesResponse searchSales (Long salesId);
    boolean deleteSales (Long salesId);
    boolean updateSales (Long salesId,SalesRequest salesRequest);
    List<SalesResponse> getAllSales ();
}
