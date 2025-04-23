package com.dev.quickcart.order_service_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerOrderRequestDto {

 private Date orderDate;
 private double totalAmount;
 private String userId;
 private ArrayList<CustomerOrderRequestDto>orderDetails;

}
