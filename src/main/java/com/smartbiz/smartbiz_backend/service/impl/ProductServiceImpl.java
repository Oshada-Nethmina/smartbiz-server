package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.ProductRequest;
import com.smartbiz.smartbiz_backend.dto.ProductResponse;
import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.entity.Product;
import com.smartbiz.smartbiz_backend.entity.Supplier;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.ProductRepo;
import com.smartbiz.smartbiz_backend.repository.SupplierRepo;
import com.smartbiz.smartbiz_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final BusinessRepo businessRepo;
    private final SupplierRepo supplierRepo;

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        // 1. Get Business
        Business business = businessRepo.findById(productRequest.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Supplier supplier = productRequest.getSupplierId() != null ? supplierRepo.findById(productRequest.getSupplierId()).orElse(null) : null;

        Product product = new Product();

        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setCost(productRequest.getCost());
        product.setQuantity(productRequest.getQuantity());
        product.setBusiness(business);  
        product.setSupplier(supplier);

        Product save = productRepo.save(product);
        return new ProductResponse(save.getProductId(), save.getName(), save.getCategory(), save.getCost(), save.getQuantity(), business.getBusinessId(), supplier != null ? supplier.getSupplierId() : null);
    }

    @Override
    public boolean updateProduct(Long businessId, Long productId, ProductRequest productRequest) {
        Product update = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        // Optional: validate business
        if (!update.getBusiness().getBusinessId().equals(businessId)) {
            throw new RuntimeException("Product does not belong to this business");
        }

        update.setName(productRequest.getName());
        update.setCategory(productRequest.getCategory());
        update.setCost(productRequest.getCost());
        update.setQuantity(productRequest.getQuantity());

        productRepo.save(update);

        return true;
    }

    @Override
    public boolean deleteProduct(Long businessId, Long productId) {
        Product delete = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        if (!delete.getBusiness().getBusinessId().equals(businessId)) {
            throw new RuntimeException("Product does not belong to this business");
        }
        productRepo.delete(delete);
        return true;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> allProducts = productRepo.findAll();
        return allProducts.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getProductId())
                .name(product.getName())
                .category(product.getCategory())
                .cost(product.getCost())
                .quantity(product.getQuantity())
                .supplierName(product.getSupplier() != null ? product.getSupplier().getName() : null)
                .createdAt(product.getCreatedAt())
                .build();
    }
}
