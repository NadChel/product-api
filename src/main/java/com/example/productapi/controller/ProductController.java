package com.example.productapi.controller;

import com.example.productapi.data.dto.ProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;
import com.example.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(ProductRequestDto productRequestDto) {
        ProductResponseDto savedProductDto = productService.save(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDto);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(ProductRequestDto productRequestDto) {
        ProductResponseDto savedProductDto = productService.save(productRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(savedProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String productId) {
        productService.deleteByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
