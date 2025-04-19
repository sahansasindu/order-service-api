package com.dev.quickcart.order_service_api.repo;

import com.dev.quickcart.order_service_api.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepo extends JpaRepository<OrderStatus,String> {
}
