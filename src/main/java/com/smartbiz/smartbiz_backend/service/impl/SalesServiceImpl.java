package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.SalesItemRequest;
import com.smartbiz.smartbiz_backend.dto.SalesItemResponse;
import com.smartbiz.smartbiz_backend.dto.SalesRequest;
import com.smartbiz.smartbiz_backend.dto.SalesResponse;
import com.smartbiz.smartbiz_backend.entity.*;
import com.smartbiz.smartbiz_backend.repository.*;
import com.smartbiz.smartbiz_backend.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesRepo salesRepo;
    private final BusinessRepo businessRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private final InvoiceItemRepo invoiceItemRepo;
    private final UserRepo userRepo;

    @Override
    public SalesResponse saveSales(SalesRequest salesRequest) {
        Business business = businessRepo.findById(salesRequest.getBusinessId()).orElseThrow();
        Customer customer = salesRequest.getCustomerId() != null ? customerRepo.findById(salesRequest.getCustomerId()).orElse(null) : null;
        User user = salesRequest.getUserId() != null ? userRepo.findById(salesRequest.getUserId()).orElse(null) : null;

        Sales sales = salesRepo.save(Sales.builder()
                .business(business).customer(customer).user(user)
                .paymentMethod(salesRequest.getPaymentMethod()).totalAmount(salesRequest.getTotalAmount())
                .salesDate(salesRequest.getSalesDate() != null ? salesRequest.getSalesDate() : LocalDateTime.now()).build());

        if (salesRequest.getItems() != null) {
            salesRequest.getItems().forEach(item -> {
                Product product = productRepo.findById(item.getProductId()).orElse(null);
                if (product != null) {
                    invoiceItemRepo.save(InvoiceItem.builder().sales(sales).product(product)
                            .quantity(item.getQuantity()).price(item.getPrice()).subtotal(item.getSubtotal()).build());
                    product.setQuantity(Math.max(0, product.getQuantity() - item.getQuantity()));
                    productRepo.save(product);
                }
            });
        }
        return toSalesResponse(sales);
    }

    @Override
    public SalesResponse searchSales(Long salesId) {
        Sales search = salesRepo.findById(salesId).orElseThrow(() -> new RuntimeException("Sales not found"));
        return toSalesResponse(search);
    }

    @Override
    public boolean deleteSales(Long salesId) {
        Sales delete = salesRepo.findById(salesId).orElseThrow(() -> new RuntimeException("Sales not found"));
        salesRepo.delete(delete);
        return true;
    }

    @Override
    public boolean updateSales(Long salesId, SalesRequest salesRequest) {
        // 1. Get existing sale
        Sales sales = salesRepo.findById(salesId)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        // 2. Restore OLD stock (undo previous sale)
        List<InvoiceItem> oldItems = invoiceItemRepo.findBySalesSalesId(salesId);

        for (InvoiceItem item : oldItems) {
            Product product = item.getProduct();
            if (product != null) {
                product.setQuantity(product.getQuantity() + item.getQuantity());
            }
        }

        // Save all restored products in one go (optimized)
        productRepo.saveAll(
                oldItems.stream()
                        .map(InvoiceItem::getProduct)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );

        // 3. Delete old items
        invoiceItemRepo.deleteAll(oldItems);

        // 4. Update sale details
        Customer customer = salesRequest.getCustomerId() != null
                ? customerRepo.findById(salesRequest.getCustomerId()).orElse(null)
                : null;

        User user = salesRequest.getUserId() != null
                ? userRepo.findById(salesRequest.getUserId()).orElse(null)
                : null;

        sales.setCustomer(customer);
        sales.setUser(user);
        sales.setPaymentMethod(salesRequest.getPaymentMethod());
        sales.setTotalAmount(salesRequest.getTotalAmount());

        if (salesRequest.getSalesDate() != null) {
            sales.setSalesDate(salesRequest.getSalesDate());
        }

        // 5. Save NEW items + reduce stock again
        List<Product> updatedProducts = new ArrayList<>();

        if (salesRequest.getItems() != null) {
            for (SalesItemRequest item : salesRequest.getItems()) {

                Product product = productRepo.findById(item.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                // 🔥 Stock validation
                if (product.getQuantity() < item.getQuantity()) {
                    throw new RuntimeException(
                            "Not enough stock for product: " + product.getName()
                    );
                }

                // Save invoice item
                invoiceItemRepo.save(InvoiceItem.builder()
                        .sales(sales)
                        .product(product)
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .subtotal(item.getSubtotal())
                        .build());

                // Reduce stock
                product.setQuantity(product.getQuantity() - item.getQuantity());
                updatedProducts.add(product);
            }
        }

        // Save all updated products
        productRepo.saveAll(updatedProducts);

        // 6. Save updated sale
        salesRepo.save(sales);

        // 7. Return response
        return true;
    }


    @Override
    public List<SalesResponse> getAllSales() {
        List<Sales> allSales = salesRepo.findAll();
        return allSales.stream()
                .map(this::toSalesResponse)
                .toList();
    }

    private SalesResponse toSalesResponse(Sales sales) {
        List<SalesItemResponse> items = invoiceItemRepo.findBySalesSalesId(sales.getSalesId()).stream().map(i ->
                SalesItemResponse.builder().productId(i.getProduct() != null ? i.getProduct().getProductId() : null)
                        .productName(i.getProduct() != null ? i.getProduct().getName() : null)
                        .quantity(i.getQuantity()).price(i.getPrice()).subtotal(i.getSubtotal()).build()
        ).collect(Collectors.toList());

        return SalesResponse.builder().id(sales.getSalesId())
                .customerName(sales.getCustomer() != null ? sales.getCustomer().getName() : "Walk-in")
                .paymentMethod(sales.getPaymentMethod()).salesDate(sales.getSalesDate())
                .totalAmount(sales.getTotalAmount()).items(items).createdAt(sales.getCreatedAt()).build();
    }
}
