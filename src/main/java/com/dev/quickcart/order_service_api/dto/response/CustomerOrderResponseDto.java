package com.dev.quickcart.order_service_api.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerOrderResponseDto {

    private String orderId;
    private Date orderDate;
    private double totalAmount;
    private String userId;
    private String remark;
    private String status;
    private List<OrderDetailsResponseDto> orderDetails;
}
