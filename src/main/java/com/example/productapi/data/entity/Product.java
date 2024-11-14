package com.example.productapi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
	@Id
	@Column(name = "id")
	private String productId;
	private Status status;
	private String fulfillmentCenter;
	private Long quantity;
	private Long value;
}
