package com.dev.quickcart.order_service_api.service.impl;

import com.dev.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dev.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.dev.quickcart.order_service_api.entity.CustomerOrder;
import com.dev.quickcart.order_service_api.entity.OrderDetails;
import com.dev.quickcart.order_service_api.entity.OrderStatus;
import com.dev.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.dev.quickcart.order_service_api.repo.OrderStatusRepo;
import com.dev.quickcart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {


    private final CustomerOrderRepo customerOrderRepo;
    private final OrderStatusRepo orderStatusRepo;


    @Override
    public void createOrder(CustomerOrderRequestDto requestDto) {

        OrderStatus orderStatus = orderStatusRepo.findByStatus("PENDING")
                .orElseThrow(() -> new RuntimeException("Order status not found"));


        CustomerOrder customerOrder=new CustomerOrder();
        customerOrder.setOrderId(UUID.randomUUID().toString());
        customerOrder.setOrderDate(requestDto.getOrderDate());
        customerOrder.setRemark("");
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        customerOrder.setUserId(requestDto.getUserId());

        customerOrder.setOrderStatus(orderStatus);
        customerOrder.setProducts(requestDto.getOrderDetails().stream().map(e->createOrderDetail(e,customerOrder)).collect(Collectors.toSet()));

        customerOrderRepo.save(customerOrder);

    }
    private OrderDetails createOrderDetail(OrderDetailRequestDto requestDto, CustomerOrder order){
        if(requestDto==null){
            return null;
        }
        return OrderDetails.builder()
                .detailId(UUID.randomUUID().toString())
                .unitPrice(requestDto.getUnitprice())
                .discount(requestDto.getDiscount())
                .qty(requestDto.getQty())
                .customerOrder(order)
                .build();


    }
}
