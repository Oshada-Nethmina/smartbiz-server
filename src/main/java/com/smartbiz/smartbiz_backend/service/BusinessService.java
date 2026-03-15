package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.BusinessResponse;

import java.util.List;

public interface BusinessService {
    BusinessResponse findById (Long id);
    List<BusinessResponse> getAll();

}
