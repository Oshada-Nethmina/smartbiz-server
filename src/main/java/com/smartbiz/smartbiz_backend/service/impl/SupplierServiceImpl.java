package com.smartbiz.smartbiz_backend.service.impl;


import com.smartbiz.smartbiz_backend.dto.SupplierRequest;
import com.smartbiz.smartbiz_backend.dto.SupplierResponse;
import com.smartbiz.smartbiz_backend.entity.Supplier;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.SupplierRepo;
import com.smartbiz.smartbiz_backend.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepo supplierRepo;
    private final BusinessRepo businessRepo;

    @Override
    public SupplierResponse searchSupplier(Long supplierId) {
        Supplier supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        return mapToResponse(supplier);
    }

    @Override
    public SupplierResponse saveSupplier(SupplierRequest supplierRequest) {
        Supplier save = supplierRepo.save(new Supplier(supplierRequest.getName(), supplierRequest.getEmail(), supplierRequest.getPhone(), businessRepo.findById(supplierRequest.getBusinessId()).orElseThrow()));
        return new SupplierResponse(save.getSupplierId(), save.getName(), save.getEmail(), save.getPhone(),save.getCreatedAt());
    }

    @Override
    public boolean deleteSupplier(Long supplierId) {
        Supplier delete = supplierRepo.findById(supplierId).orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplierRepo.delete(delete);
        return true;
    }

    @Override
    public boolean updateSupplier(Long supplierId, SupplierRequest supplierRequest) {
        Supplier update = supplierRepo.findById(supplierId).orElseThrow(() -> new RuntimeException("Supplier not found"));
        update.setName(supplierRequest.getName());
        update.setEmail(supplierRequest.getEmail());
        update.setPhone(supplierRequest.getPhone());

        supplierRepo.save(update);

        return true;
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {
        List<Supplier> allSuppliers = supplierRepo.findAll();
        return allSuppliers.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SupplierResponse mapToResponse (Supplier supplier){
        return SupplierResponse.builder()
                .id(supplier.getSupplierId())
                .name(supplier.getName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .createdAt(supplier.getCreatedAt())
                .build();

    }
}
