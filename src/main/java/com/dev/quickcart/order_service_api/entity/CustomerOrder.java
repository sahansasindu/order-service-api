package com.dev.quickcart.order_service_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="customer_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerOrder {
    @Id
    @Column(name="order_id",length = 80)
    private String orderId;
    @Column(name="order_date",nullable = false,columnDefinition ="DATETIME")
    private Date orderDate;
    @Column(name="total_amount",nullable = false,precision = 10,scale = 2)
    private String totalAmount;
    @Column(name="user_id",nullable = false,length=80)
    private String userId;
    @Column(name="remark",length = 750)
    private String remark;

}
