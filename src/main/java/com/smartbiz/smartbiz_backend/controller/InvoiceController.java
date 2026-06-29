package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.InvoiceResponse;
import com.smartbiz.smartbiz_backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<InvoiceResponse>>> getAllInvoices(
            @RequestParam Long businessId) {

        return ResponseEntity.ok(
                ApiResponse.ok(invoiceService.getAllInvoices(businessId))
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse<InvoiceResponse>> findInvoice(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.ok(invoiceService.findInvoice(id))
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
