package com.example.productapi.controller;

import com.example.productapi.data.dto.NewProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;
import com.example.productapi.data.dto.UpdatedProductRequestDto;
import com.example.productapi.data.entity.Status;
import com.example.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProductsByStatus(@RequestParam(required = false) Status status) {
        List<ProductResponseDto> productsByStatus = productService.findByStatus(status);
        return ResponseEntity.ok(productsByStatus);
    }

    @GetMapping("/sum")
    public ResponseEntity<Long> getSumOfValues(@RequestParam(required = false) Status status,
                                               @RequestParam(required = false) String fulfillmentCenterId) {
        Long sumOfValuesByStatus = productService.getSumOfValues(status, fulfillmentCenterId);
        return ResponseEntity.ok(sumOfValuesByStatus);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody NewProductRequestDto dto) {
        ProductResponseDto savedProductDto = productService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDto);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody UpdatedProductRequestDto dto) {
        ProductResponseDto savedProductDto = productService.update(dto);
        return ResponseEntity.ok(savedProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String productId) {
        productService.deleteByProductId(productId);
        return ResponseEntity.ok().build();
    }
}
