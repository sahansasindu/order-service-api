package com.dev.quickcart.order_service_api.repo;

import com.dev.quickcart.order_service_api.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetails,String> {
}
