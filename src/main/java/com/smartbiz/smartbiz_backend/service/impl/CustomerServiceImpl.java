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
    public boolean updateCustomer(CustomerRequest customerRequest) {
        Customer update = customerRepo.findById(customerRequest.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
        update.setName(customerRequest.getName());
        update.setEmail(customerRequest.getEmail());
        update.setAddress(customerRequest.getAddress());
        customerRepo.save(update);
        return true;
    }

    @Override
    public boolean deleteCustomer(Long id) {
        Customer delete = customerRepo.findById(id).orElseThrow();
        customerRepo.delete(delete);
        return true;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> allCustomers = customerRepo.findAll();
        return allCustomers.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getCustomerId())
                .name(customer.getName())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .build();
    }
}
