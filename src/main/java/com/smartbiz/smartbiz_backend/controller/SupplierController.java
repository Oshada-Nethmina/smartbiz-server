package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.SupplierRequest;
import com.smartbiz.smartbiz_backend.dto.SupplierResponse;
import com.smartbiz.smartbiz_backend.service.SupplierService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("/search/{supplierId}")
    public ResponseEntity<ApiResponse<SupplierResponse>> searchSupplier(@PathVariable Long supplierId) {
        SupplierResponse search = supplierService.searchSupplier(supplierId);
        return ResponseEntity.ok(ApiResponse.ok(search));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<SupplierResponse>> saveSupplier(@RequestBody SupplierRequest supplierRequest) {
        SupplierResponse save = supplierService.saveSupplier(supplierRequest);
        return ResponseEntity.ok(ApiResponse.ok(save));
    }

    @DeleteMapping("/delete/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long supplierId) {
        boolean delete = supplierService.deleteSupplier(supplierId);
        if (delete) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{supplierId}")
    public ResponseEntity<Void> updateSupplier(@PathVariable Long supplierId, @RequestBody SupplierRequest supplierRequest) {
        boolean update = supplierService.updateSupplier(supplierId,supplierRequest);
        if (update) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<SupplierResponse>>> getAllSupplier () {
        List<SupplierResponse> allSuppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(ApiResponse.ok(allSuppliers));
    }


}
