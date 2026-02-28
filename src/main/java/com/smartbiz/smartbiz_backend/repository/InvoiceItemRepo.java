package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceItemRepo extends JpaRepository<InvoiceItem,Long> {
    List<InvoiceItem> findBySalesId(Long salesId);
}
