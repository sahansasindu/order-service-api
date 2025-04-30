package com.dev.quickcart.order_service_api.service.impl;

import com.dev.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dev.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.dev.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;
import com.dev.quickcart.order_service_api.dto.response.OrderDetailsResponseDto;
import com.dev.quickcart.order_service_api.dto.response.paginate.CustomerOrderPaginateDto;
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

    @Override
    public CustomerOrderResponseDto findOrderById(String orderId) {
        CustomerOrder customerOrder=customerOrderRepo.findById(orderId).orElseThrow(()->new RuntimeException(String.format("Order not found with %s",orderId)));
        return toCustomerOrderResponseDto(customerOrder);
    }

    @Override
    public void deleteById(String orderId) {

        CustomerOrder customerOrder=customerOrderRepo.findById(orderId).orElseThrow(()->new RuntimeException(String.format("Order not found with %s",orderId)));
       customerOrderRepo.delete(customerOrder);

    }

    @Override
    public CustomerOrderPaginateDto searchAll(String searchText, int page, int size) {
        return null;
    }

    private CustomerOrderResponseDto toCustomerOrderResponseDto(CustomerOrder customerOrder){
        if(customerOrder==null){
            return  null;
        }
        return CustomerOrderResponseDto.builder()
                .orderId(customerOrder.getOrderId())
                .orderDate(customerOrder.getOrderDate())
                .userId(customerOrder.getUserId())
                .totalAmount(customerOrder.getTotalAmount())
                .orderDetails(

                        customerOrder.getProducts().stream().map(this::toOrderDetailResponseDto).collect(Collectors.toList())

                )
                .remark(customerOrder.getRemark())
                .status(customerOrder.getOrderStatus().getStatus())
                .build();
    }

    private OrderDetailsResponseDto toOrderDetailResponseDto(OrderDetails orderDetails){
        if(orderDetails==null){
            return null;
        }
        return OrderDetailsResponseDto.builder()
                .productId(orderDetails.getProductId())
                .detailId(orderDetails.getDetailId())
                .discount(orderDetails.getDiscount())
                .qty(orderDetails.getQty())
                .unitprice(orderDetails.getUnitPrice())
                .build();

    }





}
