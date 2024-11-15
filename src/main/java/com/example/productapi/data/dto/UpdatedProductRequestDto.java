package com.example.productapi.data.dto;

import com.example.productapi.data.entity.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UpdatedProductRequestDto {
    private String productId;
    private Status status;
    private String fulfillmentCenterId;
    private Long quantity;
    private Long value;
}
