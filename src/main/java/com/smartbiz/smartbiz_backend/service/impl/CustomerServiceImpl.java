package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.CustomerRequest;
import com.smartbiz.smartbiz_backend.dto.CustomerResponse;
import com.smartbiz.smartbiz_backend.entity.Business;
import com.smartbiz.smartbiz_backend.entity.Customer;
import com.smartbiz.smartbiz_backend.repository.BusinessRepo;
import com.smartbiz.smartbiz_backend.repository.CustomerRepo;
import com.smartbiz.smartbiz_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final BusinessRepo businessRepo;

    @Override
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Business business = businessRepo.findById(customerRequest.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setBusiness(business);

        Customer save = customerRepo.save(customer);

        return mapToResponse(save);
    }

    @Override
    public CustomerResponse findCustomer(Long id) {
        return customerRepo.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public boolean updateCustomer(Long id, CustomerRequest customerRequest) {

        Customer update = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Business business = businessRepo.findById(customerRequest.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        update.setName(customerRequest.getName());
        update.setEmail(customerRequest.getEmail());
        update.setAddress(customerRequest.getAddress());
        update.setPhoneNumber(customerRequest.getPhoneNumber());
        update.setBusiness(business);

        customerRepo.save(update);

        return true;
    }


    @Override
    public void deleteCustomer(Long id) {
        Customer delete = customerRepo.findById(id).orElseThrow();
        customerRepo.delete(delete);
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
                .phone(customer.getPhoneNumber())
                .email(customer.getEmail())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
