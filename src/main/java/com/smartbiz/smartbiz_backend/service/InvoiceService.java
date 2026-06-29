package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.InvoiceResponse;

import java.util.List;

public interface InvoiceService {
    List<InvoiceResponse> getAllInvoices(Long businessId);
    InvoiceResponse findInvoice(Long salesId);
    void deleteInvoice(Long salesId);
}
