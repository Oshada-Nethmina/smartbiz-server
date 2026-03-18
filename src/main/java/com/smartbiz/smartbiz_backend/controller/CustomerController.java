package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.CustomerRequest;
import com.smartbiz.smartbiz_backend.dto.CustomerResponse;
import com.smartbiz.smartbiz_backend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CustomerResponse>> saveCustomer(@Valid@RequestBody CustomerRequest customerRequest){
        CustomerResponse save = customerService.saveCustomer(customerRequest);
        return ResponseEntity.ok(ApiResponse.ok(save));
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateCustomer (@RequestBody CustomerRequest customerRequest){
        boolean update = customerService.updateCustomer(customerRequest);
        if (update){
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
