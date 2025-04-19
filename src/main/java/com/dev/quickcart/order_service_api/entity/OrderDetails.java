package com.dev.quickcart.order_service_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="order_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetails {
    @Id
    @Column(name="detail_id",length = 80)
    private String orderId;
    @Column(name="product_id",nullable = false,length=80)
    private String productId;
    @Column(name = "qty",nullable = false)
    private int qty;
    @Column(name="unit_price",nullable = false,precision = 10,scale = 2)
    private String unitPrice;
    @Column(name="discount",precision = 10,scale = 2)
    private double discount;
    @Column(name="user_id",nullable = false,length=80)
    private String userId;
    @Column(name="remark",length = 750)
    private String remark;

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;




}
