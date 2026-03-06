package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByBusinessBusinessId(Long businessId);
    List<Product> findByBusinessBusinessIdAndQuantityLessThanEqual(Long businessId, Integer quantity);
}
