package com.example.productapi.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "p-id-generator")
	@GenericGenerator(name = "p-id-generator", strategy = "com.example.productapi.generator.PIdGenerator")
	private String productId;
	private Status status;
	@Column(name = "fulfillment_center_id")
	private String fulfillmentCenterId;
	@Column(name = "qty")
	private Long quantity;
	private Long value;
}
