package com.example.productapi.mapper;

import com.example.productapi.data.dto.ProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;
import com.example.productapi.data.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
	Product toProduct(ProductRequestDto dto);
	Product toProduct(ProductResponseDto dto);
	ProductResponseDto toResponseDto(Product product);
}
