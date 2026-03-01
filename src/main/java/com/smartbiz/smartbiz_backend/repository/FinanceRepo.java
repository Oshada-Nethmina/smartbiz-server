package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceRepo extends JpaRepository<Finance,Long> {
}
