package com.dev.quickcart.order_service_api.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequestDto {
    private String productId;
    private int qty;
    private BigDecimal unitprice;
    private BigDecimal discount;
}
