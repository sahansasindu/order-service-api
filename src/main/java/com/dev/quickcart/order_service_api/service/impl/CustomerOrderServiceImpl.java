package com.dev.quickcart.order_service_api.service.impl;

import com.dev.quickcart.order_service_api.dto.request.CustomerOrderRequestDto;
import com.dev.quickcart.order_service_api.dto.request.OrderDetailRequestDto;
import com.dev.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;
import com.dev.quickcart.order_service_api.dto.response.OrderDetailsResponseDto;
import com.dev.quickcart.order_service_api.dto.response.paginate.CustomerOrderPaginateDto;
import com.dev.quickcart.order_service_api.entity.CustomerOrder;
import com.dev.quickcart.order_service_api.entity.OrderDetails;
import com.dev.quickcart.order_service_api.entity.OrderStatus;
import com.dev.quickcart.order_service_api.exception.EntryNotFoundException;
import com.dev.quickcart.order_service_api.repo.CustomerOrderRepo;
import com.dev.quickcart.order_service_api.repo.OrderStatusRepo;
import com.dev.quickcart.order_service_api.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepo customerOrderRepo;
    private final OrderStatusRepo orderStatusRepo;
    private final JwtService jwtService;

    @Override
    @Transactional
    public void createOrder(CustomerOrderRequestDto requestDto,String tokenHeader) {

        try {

        String userId = getUserIdFromToken(tokenHeader);

        OrderStatus orderStatus = orderStatusRepo.findByStatus("PENDING")
                .orElseThrow(() -> new EntryNotFoundException("Order status not found"));


        double calculatedTotal = calculateOrderTotal(requestDto.getOrderDetails());

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderDate(new Date());
        customerOrder.setRemark("");
        customerOrder.setTotalAmount(calculatedTotal);
        customerOrder.setUserId(userId);

        customerOrder.setOrderStatus(orderStatus);
        customerOrder.setProducts(requestDto.getOrderDetails().stream().map(e -> createOrderDetail(e, customerOrder))
                .collect(Collectors.toSet()));

        customerOrderRepo.save(customerOrder);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create order: " + e.getMessage(), e);
        }

    }

    private String getUserIdFromToken(String tokenHeader) {
        try {
            // Validate token header
            if (tokenHeader == null || tokenHeader.trim().isEmpty()) {
                throw new IllegalArgumentException("Authorization token is required");
            }
            
            String token = tokenHeader;
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            
            // Extract user ID from JWT token
            return jwtService.getUserId(token);

        } catch (Exception e) {
            throw new IllegalStateException("Invalid or expired token", e);
        }
    }

    // Method to calculate total amount from order details
    private double calculateOrderTotal(List<OrderDetailRequestDto> orderDetails) {
        double cost = 0;
        for (OrderDetailRequestDto d : orderDetails) {
            cost += d.getUnitprice() - d.getDiscount();
        }
        return cost;
    }


    @Override
    @Transactional
    public void updateOrder(CustomerOrderRequestDto requestDto, String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));
        customerOrder.setOrderDate(new Date());
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        customerOrderRepo.save(customerOrder);

    }

    @Override
    @Transactional
    public void manageRemark(String remark, String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));
        customerOrder.setRemark(remark);
        customerOrderRepo.save(customerOrder);

    }

    @Override
    @Transactional
    public void manageStatus(String status, String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));

        OrderStatus orderStatus = orderStatusRepo.findByStatus(status)
                .orElseThrow(() -> new RuntimeException("Order status not found"));

        customerOrder.setOrderStatus(orderStatus);
        customerOrderRepo.save(customerOrder);

    }

    private OrderDetails createOrderDetail(OrderDetailRequestDto requestDto, CustomerOrder order) {
        if (requestDto == null) {
            return null;
        }
        return OrderDetails.builder()
                .productId(requestDto.getProductId())
                .unitPrice(requestDto.getUnitprice())
                .discount(requestDto.getDiscount())
                .qty(requestDto.getQty())
                .customerOrder(order)
                .build();

    }

    @Override
    public CustomerOrderResponseDto findOrderById(String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));
        return toCustomerOrderResponseDto(customerOrder);
    }

    @Override
    public void deleteById(String orderId) {

        CustomerOrder customerOrder = customerOrderRepo.findById(orderId)
                .orElseThrow(() -> new EntryNotFoundException(String.format("Order not found with %s", orderId)));
        customerOrderRepo.delete(customerOrder);

    }

    @Override
    public CustomerOrderPaginateDto searchAll(String searchText, int page, int size) {
        return CustomerOrderPaginateDto.builder()
                .count(
                        customerOrderRepo.searchCount(searchText))
                .dataList(
                        customerOrderRepo.searchALL(searchText, PageRequest.of(page, size))
                                .stream().map(this::toCustomerOrderResponseDto).collect(Collectors.toList()))
                .build();
    }

    private CustomerOrderResponseDto toCustomerOrderResponseDto(CustomerOrder customerOrder) {
        if (customerOrder == null) {
            return null;
        }
        return CustomerOrderResponseDto.builder()
                .orderId(customerOrder.getOrderId())
                .orderDate(customerOrder.getOrderDate())
                .userId(customerOrder.getUserId())
                .totalAmount(customerOrder.getTotalAmount())
                .orderDetails(

                        customerOrder.getProducts().stream().map(this::toOrderDetailResponseDto)
                                .collect(Collectors.toList())

                )
                .remark(customerOrder.getRemark())
                .status(customerOrder.getOrderStatus().getStatus())
                .build();
    }

    private OrderDetailsResponseDto toOrderDetailResponseDto(OrderDetails orderDetails) {
        if (orderDetails == null) {
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
