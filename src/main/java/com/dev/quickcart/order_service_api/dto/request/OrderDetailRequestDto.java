package com.dev.quickcart.order_service_api.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequestDto {
    private String productId;
    private int qty;
    private double unitprice;
    private double discount;
}
