package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepo extends JpaRepository<Business,Long> {
}
