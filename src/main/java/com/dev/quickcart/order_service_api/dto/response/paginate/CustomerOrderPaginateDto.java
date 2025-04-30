package com.dev.quickcart.order_service_api.dto.response.paginate;

import com.dev.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;
import lombok.Builder;

import java.util.List;

@Builder

public class CustomerOrderPaginateDto {
    private long count;
    private List<CustomerOrderResponseDto>dataList;
}
