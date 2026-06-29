package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.ApiResponse;
import com.smartbiz.smartbiz_backend.dto.CustomerRequest;
import com.smartbiz.smartbiz_backend.dto.CustomerResponse;
import com.smartbiz.smartbiz_backend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CustomerResponse>> saveCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        CustomerResponse save = customerService.saveCustomer(customerRequest);
        return ResponseEntity.ok(ApiResponse.ok(save));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateCustomer (@PathVariable Long id,@Valid @RequestBody CustomerRequest customerRequest){
        boolean update = customerService.updateCustomer(id,customerRequest);
        if (update){
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> findCustomer(@PathVariable Long id) {
        CustomerResponse customer = customerService.findCustomer(id);
        return ResponseEntity.ok(ApiResponse.ok(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers (){
        return ResponseEntity.ok(
                ApiResponse.ok(customerService.getAllCustomers())
        );
    }
}
