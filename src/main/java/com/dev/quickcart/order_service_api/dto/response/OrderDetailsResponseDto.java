package com.dev.quickcart.order_service_api.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsResponseDto {

    private String detailId;
    private String productId;
    private int qty;
    private BigDecimal unitprice;
    private BigDecimal discount;
}
