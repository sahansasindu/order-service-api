package com.dev.quickcart.order_service_api.config;

import com.dev.quickcart.order_service_api.entity.OrderStatus;
import com.dev.quickcart.order_service_api.repo.OrderStatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OrderStatusRepo orderStatusRepo;

    @Override
    public void run(String... args) throws Exception {
        List<String> statuses = Arrays.asList("PENDING", "COMPLETED", "CANCELLED", "PLACED", "SHIPPED", "OUT_FOR_DELIVERY", "DELIVERED");

        for (String status : statuses) {
            if (orderStatusRepo.findByStatus(status).isEmpty()) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setStatusId(UUID.randomUUID().toString());
                orderStatus.setStatus(status);
                orderStatusRepo.save(orderStatus);
            }
        }
    }
}
