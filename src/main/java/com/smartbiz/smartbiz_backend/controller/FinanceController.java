package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.*;
import com.smartbiz.smartbiz_backend.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/finance")
@RequiredArgsConstructor
public class FinanceController {
    private final FinanceService financeService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<FinanceResponse>> saveFinance (@RequestBody FinanceRequest financeRequest) {
        FinanceResponse save = financeService.saveFinance(financeRequest);
        return ResponseEntity.ok(ApiResponse.ok(save));
    }

    @GetMapping("/search/{financeId}")
    public ResponseEntity<ApiResponse<FinanceResponse>> searchFinance(@PathVariable Long financeId) {
        FinanceResponse search = financeService.searchFinance(financeId);
        return ResponseEntity.ok(ApiResponse.ok(search));
    }

    @PutMapping("/update/{financeId}")
    public ResponseEntity<Void> updateFinance (@PathVariable Long financeId, @RequestBody FinanceRequest financeRequest) {
        boolean update = financeService.updateFinance(financeId, financeRequest);
        if (update) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{financeId}")
    public ResponseEntity<Void> deleteFinance (@PathVariable Long financeId) {
        boolean delete = financeService.deleteFinance(financeId);
        if (delete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<FinanceResponse>>> getFinance() {
        List<FinanceResponse> allFinance = financeService.getAllFinances();
        return ResponseEntity.ok(ApiResponse.ok(allFinance));
    }

}
