package com.dev.quickcart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "order_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "detail_id", length = 80)
    private String detailId;
    @Column(name = "product_id", nullable = false, length = 80)
    private String productId;
    @Column(name = "qty", nullable = false)
    private int qty;
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;
    @Column(name = "discount", precision = 10, scale = 2)
    private BigDecimal discount;
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

}
