package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepo extends JpaRepository<Sales,Long> {
    List<Sales> findByBusinessId(Long businessId);
}
