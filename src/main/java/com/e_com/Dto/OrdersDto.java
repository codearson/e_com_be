package com.e_com.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: OrdersDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 21:59:27
 * @version 1.0
 **/

@Data
public class OrdersDto {

    private Integer id;

    private ProductDto productDto;

    private UserDto userDto;

    private PostagePartnerDto postagePartnerDto;

    private ShippingAddressDto shippingAddressDto;

    private StatusDto statusDto;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime estimateDeliveryDate;

    private Integer quantity;

    private Boolean isActive;
    
}
