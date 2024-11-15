package com.example.productapi.service;

import com.example.productapi.data.dto.NewProductRequestDto;
import com.example.productapi.data.dto.UpdatedProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;

import com.example.productapi.data.entity.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponseDto save(NewProductRequestDto dto);

    ProductResponseDto update(UpdatedProductRequestDto dto);

    void deleteByProductId(String productId);

    List<ProductResponseDto> findByStatus(Status status);

    Long getSumOfValues(Status status, String fulfillmentCenterId);
}
