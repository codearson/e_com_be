package com.e_com.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: CartDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 01 August 2025
 * @time 6:52:55 PM
 * @version 1.0
 **/

@Data
public class CartDto {
    
    private Integer id;
    
    private Integer userId;
    
    private Integer productId;
    
    private Integer quantity;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private Boolean isActive;
    
    private ProductDto product;
    
    private Double totalPrice;
} 