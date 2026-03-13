package com.dev.quickcart.order_service_api.dto.response.paginate;

import com.dev.quickcart.order_service_api.dto.response.CustomerOrderResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderPaginateDto {
    private long count;
    private List<CustomerOrderResponseDto>dataList;
}
