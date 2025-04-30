package com.dev.quickcart.order_service_api.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsResponseDto {

    private String detailId;
    private String productId;
    private int qty;
    private double unitprice;
    private double discount;
}
