package com.dev.quickcart.order_service_api.dto.request;

import java.util.ArrayList;
import java.util.Date;

public class OrderDetailRequestDto {
    private Date orderDate;
    private String totalAmount;
    private String userId;
    private ArrayList<OrderDetailRequestDto> orderDetails;
}
