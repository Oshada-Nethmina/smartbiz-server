package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.ProductRequest;
import com.smartbiz.smartbiz_backend.dto.ProductResponse;
import com.smartbiz.smartbiz_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<ProductResponse>> saveProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse save = productService.saveProduct(productRequest);
        return ResponseEntity.ok(ApiResponse.ok(save));
    }

    @PutMapping("/update/business/{businessId}/{productId}")
    public ResponseEntity<Void> updateProduct (@PathVariable Long businessId, @PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        boolean update = productService.updateProduct(businessId, productId,productRequest);
        if (update) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/business/{businessId}/{productId}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long businessId, @PathVariable Long productId) {
        boolean delete = productService.deleteProduct(businessId, productId);
        if (delete) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getAllProduct () {
        List<ProductResponse> allProducts = productService.getAllProduct();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
}