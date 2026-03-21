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

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateProduct (@PathVariable Long id) {
        boolean update = productService.updateProduct(id);
        if (update) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id) {
        boolean delete = productService.deleteProduct(id);
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