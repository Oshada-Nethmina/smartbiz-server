package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
