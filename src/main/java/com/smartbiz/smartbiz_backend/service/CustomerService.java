package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.CustomerRequest;
import com.smartbiz.smartbiz_backend.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse saveCustomer (CustomerRequest customerRequest);
    CustomerResponse updateCustomer (CustomerRequest customerRequest);
    boolean deleteCustomer (Long id);
    List<CustomerResponse> getAllCustomers();
}
