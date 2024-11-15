package com.example.productapi.service;

import com.example.productapi.data.dto.NewProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;
import com.example.productapi.data.dto.UpdatedProductRequestDto;
import com.example.productapi.data.entity.Product;
import com.example.productapi.data.entity.Status;
import com.example.productapi.mapper.ProductMapper;
import com.example.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    @Transactional(readOnly = false)
    public ProductResponseDto save(NewProductRequestDto dto) {
        Product product = mapper.toProduct(dto);
        Product savedProduct = productRepository.save(product);
        return mapper.toResponseDto(savedProduct);
    }

    @Override
    @Transactional(readOnly = false)
    public ProductResponseDto update(UpdatedProductRequestDto dto) {
        Product product = mapper.toProduct(dto);
        Product updatedProduct = productRepository.save(product);
        return mapper.toResponseDto(updatedProduct);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteByProductId(String productId) {
        productRepository.deleteByProductId(productId);
    }

    @Override
    public List<ProductResponseDto> findByStatus(Status status) {
        List<Product> productsByStatus = productRepository.findByStatus(status);
        List<ProductResponseDto> productDtos = productsByStatus.stream()
                .map(mapper::toResponseDto)
                .toList();
        return productDtos;
    }

    @Override
    public Long getSumOfValues(Status status, String fulfillmentCenterId) {
        Long sumOfValues = productRepository.getSumOfValues(status, fulfillmentCenterId);
        return sumOfValues;
    }
}
