package com.example.productapi.repository;

import com.example.productapi.data.entity.Product;

import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(idClass = String.class, domainClass = Product.class)
public interface ProductRepository {
	Product save(Product dto);
	void deleteByProductId(String productId);
}
