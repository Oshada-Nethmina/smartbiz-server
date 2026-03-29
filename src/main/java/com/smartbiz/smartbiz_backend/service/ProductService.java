package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.ProductRequest;
import com.smartbiz.smartbiz_backend.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse saveProduct (ProductRequest productRequest);
    boolean updateProduct(Long businessId, Long productId, ProductRequest productRequest);
    boolean deleteProduct (Long businessId, Long productId);
    List<ProductResponse> getAllProduct ();


}
