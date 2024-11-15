package com.example.productapi.mapper;

import com.example.productapi.data.dto.ProductRequestDto;
import com.example.productapi.data.dto.ProductResponseDto;
import com.example.productapi.data.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
	Product toProduct(ProductRequestDto dto);
	ProductResponseDto toResponseDto(Product product);
}
