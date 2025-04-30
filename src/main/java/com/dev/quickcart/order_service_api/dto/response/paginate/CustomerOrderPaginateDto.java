package com.dev.quickcart.order_service_api.dto.response.paginate;

import com.dev.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;

import java.util.List;

public class CustomerOrderPaginateDto {
    private long count;
    private List<CustomerOrderResponseDto>dataList;
}
