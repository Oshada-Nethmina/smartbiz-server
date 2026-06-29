package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByBusiness_BusinessId(Long businessId);

    List<Product> findByBusiness_BusinessIdAndQuantityLessThanEqual(
            Long businessId,
            Integer threshold
    );
}
