package com.dev.quickcart.order_service_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequestDto {
    private String productId;
    
    @JsonProperty("quantity")
    private int qty;
    
    @JsonProperty("price")
    private double unitprice;
    
    private double discount;
}
