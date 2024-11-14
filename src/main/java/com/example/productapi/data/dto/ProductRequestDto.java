package com.example.productapi.data.dto;

import com.example.productapi.data.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
	private Status status;
	private String fulfillmentCenterId;
	private Long quantity;
	private Long value;
}
