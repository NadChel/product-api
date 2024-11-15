package com.example.productapi.service;

import com.example.productapi.data.dto.NewProductRequestDto;
import com.example.productapi.data.dto.UpdatedProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductResponseDto save(NewProductRequestDto dto);

    ProductResponseDto update(UpdatedProductRequestDto dto);

    void deleteByProductId(String productId);
}
