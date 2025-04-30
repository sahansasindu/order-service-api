package com.dev.quickcart.order_service_api.service;

import com.dev.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dev.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;

public interface CustomerOrderService {


    public void createOrder(CustomerOrderRequestDto customerOrderRequestDto);
    public CustomerOrderResponseDto findOrderById(String orderId);
}
