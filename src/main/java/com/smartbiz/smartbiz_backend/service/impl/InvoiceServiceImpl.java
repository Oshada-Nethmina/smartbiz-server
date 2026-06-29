package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.InvoiceResponse;
import com.smartbiz.smartbiz_backend.dto.SalesItemResponse;
import com.smartbiz.smartbiz_backend.entity.Sales;
import com.smartbiz.smartbiz_backend.repository.InvoiceItemRepo;
import com.smartbiz.smartbiz_backend.repository.SalesRepo;
import com.smartbiz.smartbiz_backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final SalesRepo salesRepo;
    private final InvoiceItemRepo invoiceItemRepo;
    @Override
    public List<InvoiceResponse> getAllInvoices(Long businessId) {
        return salesRepo.findByBusiness_BusinessId(businessId)
                .stream()
                .map(this::mapToInvoiceResponse)
                .toList();

    }

    @Override
    public InvoiceResponse findInvoice(Long salesId) {
        Sales sales = salesRepo.findById(salesId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        return mapToInvoiceResponse(sales);
    }

    @Override
    public void deleteInvoice(Long salesId) {
        Sales sales = salesRepo.findById(salesId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        salesRepo.delete(sales);
    }

    private InvoiceResponse mapToInvoiceResponse(Sales sales) {

        List<SalesItemResponse> items =
                invoiceItemRepo.findBySalesSalesId(sales.getSalesId())
                        .stream()
                        .map(item -> SalesItemResponse.builder()
                                .productId(item.getProduct().getProductId())
                                .productName(item.getProduct().getName())
                                .quantity(item.getQuantity())
                                .price(item.getPrice())
                                .subtotal(item.getSubtotal())
                                .build())
                        .toList();

        return InvoiceResponse.builder()
                .id(sales.getSalesId())
                .salesId(sales.getSalesId())
                .customerName(
                        sales.getCustomer() != null
                                ? sales.getCustomer().getName()
                                : "Walk-in"
                )
                .customerEmail(
                        sales.getCustomer() != null
                                ? sales.getCustomer().getEmail()
                                : null
                )
                .paymentMethod(sales.getPaymentMethod())
                .totalAmount(sales.getTotalAmount())
                .items(items)
                .createdAt(sales.getCreatedAt())
                .build();
    }
}
