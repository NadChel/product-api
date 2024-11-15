package com.example.productapi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = IDENTITY)
	private String productId;
	private Status status;
	@Column(name = "fulfillment_center_id")
	private String fulfillmentCenterId;
	@Column(name = "qty")
	private Long quantity;
	private Long value;
}
