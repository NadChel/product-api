package com.example.productapi.service;

import com.example.productapi.data.dto.ProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {
	ProductResponseDto save(ProductRequestDto dto);
	void deleteByProductId(String productId);
}
