package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.SupplierRequest;
import com.smartbiz.smartbiz_backend.dto.SupplierResponse;

import java.util.List;

public interface SupplierService {
    SupplierResponse searchSupplier(Long supplierId);
    SupplierResponse saveSupplier(SupplierRequest supplierRequest);
    boolean deleteSupplier(Long supplierId);
    boolean updateSupplier(Long supplierId, SupplierRequest supplierRequest);
    List<SupplierResponse> getAllSuppliers();
}
