package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.ProductRequest;
import com.smartbiz.smartbiz_backend.dto.ProductResponse;
import com.smartbiz.smartbiz_backend.entity.Product;
import com.smartbiz.smartbiz_backend.repository.ProductRepo;
import com.smartbiz.smartbiz_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public boolean updateProduct(Long id) {
        return false;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return List.of();
    }
}
