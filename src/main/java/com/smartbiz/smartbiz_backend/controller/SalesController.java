package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.SalesRequest;
import com.smartbiz.smartbiz_backend.dto.SalesResponse;
import com.smartbiz.smartbiz_backend.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<SalesResponse>> saveSales(@RequestBody SalesRequest salesRequest) {
        SalesResponse save = salesService.saveSales(salesRequest);
        return ResponseEntity.ok(ApiResponse.ok(save));
    }

    @GetMapping("/search/{salesId}")
    public ResponseEntity<ApiResponse<SalesResponse>> searchSales (@PathVariable Long salesId) {
        SalesResponse search = salesService.searchSales(salesId);
        return ResponseEntity.ok(ApiResponse.ok(search));
    }

    @DeleteMapping("/delete/{salesId}")
    public ResponseEntity<Void> deleteSales (@PathVariable Long salesId) {
        boolean delete = salesService.deleteSales(salesId);
        if (delete) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ApiResponse<List<SalesResponse>>> getAllSales () {
        List<SalesResponse> allSales = salesService.getAllSales();
        return ResponseEntity.ok(ApiResponse.ok(allSales));
    }
}
