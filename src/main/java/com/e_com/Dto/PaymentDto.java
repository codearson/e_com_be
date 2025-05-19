package com.e_com.Dto;

import lombok.Data;

/**
 * Title: PaymentDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 19, 2025
 * @time 5:57:06 PM
 * @version 1.0
 **/

@Data
public class PaymentDto {

    private Integer id;

    private Double amount;

    private OrdersDto ordersDto;

    private String status;
}
