package com.example.productapi.repository;

import com.example.productapi.data.entity.Product;
import com.example.productapi.data.entity.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(idClass = String.class, domainClass = Product.class)
public interface ProductRepository {
    Product save(Product dto);

    void deleteByProductId(String productId);

	List<Product> findByStatus(Status status);

    @Query("""
            SELECT SUM(p.value)
            FROM Product p
            WHERE p.status = :status
            """)
    Long getSumOfValuesByStatus(@Param("status") Status status);
}
