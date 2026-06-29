package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.CustomerRequest;
import com.smartbiz.smartbiz_backend.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse saveCustomer (CustomerRequest customerRequest);
    CustomerResponse findCustomer (Long id);
    boolean updateCustomer (Long id, CustomerRequest customerRequest);
    void deleteCustomer (Long id);
    List<CustomerResponse> getAllCustomers();
}
