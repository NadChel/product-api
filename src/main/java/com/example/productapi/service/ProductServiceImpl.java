package com.example.productapi.service;

import com.example.productapi.data.dto.NewProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;
import com.example.productapi.data.dto.UpdatedProductRequestDto;
import com.example.productapi.data.entity.Product;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public ProductResponseDto save(NewProductRequestDto dto) {
        Product product = mapper.toProduct(dto);
        Product savedProduct = productRepository.save(product);
        return mapper.toResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto update(UpdatedProductRequestDto dto) {
        Product product = mapper.toProduct(dto);
        Product updatedProduct = productRepository.save(product);
        return mapper.toResponseDto(updatedProduct);
    }

    @Override
    public void deleteByProductId(String productId) {
        productRepository.deleteByProductId(productId);
    }
}
