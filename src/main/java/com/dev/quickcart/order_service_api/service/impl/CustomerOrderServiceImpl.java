package com.dev.quickcart.order_service_api.service.impl;

import com.dev.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dev.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.dev.quickcart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {


    private final CustomerOrderRepo customerOrderRepo;


    @Override
    public void createOrder(CustomerOrderRequestDto customerOrderRequestDto) {

    }
}
