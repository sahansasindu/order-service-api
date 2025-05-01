package com.dev.quickcart.order_service_api.utill;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StandardResponseDto {
    private int status;
    private String message;
    private Object date;
}
