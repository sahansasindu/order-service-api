package com.dev.quickcart.order_service_api.dto.response;

import com.dev.quickcart.order_service_api.dto.request.OrderDetailRequestDto;

import java.util.ArrayList;
import java.util.Date;

public class CustomerOrderResponseDto {

    private String orderId;
    private Date orderDate;
    private double totalAmount;
    private String userId;
    private String remark;
    private String status;
    private ArrayList<OrderDetailRequestDto> orderDetails;
}
