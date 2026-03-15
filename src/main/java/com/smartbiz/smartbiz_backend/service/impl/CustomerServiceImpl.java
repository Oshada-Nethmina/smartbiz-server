package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.CustomerRequest;
import com.smartbiz.smartbiz_backend.dto.CustomerResponse;
import com.smartbiz.smartbiz_backend.entity.Customer;
import com.smartbiz.smartbiz_backend.repository.CustomerRepo;
import com.smartbiz.smartbiz_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer save = customerRepo.save(new Customer(customerRequest.getName(), customerRequest.getEmail(), customerRequest.getAddress()));
        return new CustomerResponse(save.getCustomerId(), save.getName(), save.getEmail(), save.getAddress());
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        return null;
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return false;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return List.of();
    }
}
