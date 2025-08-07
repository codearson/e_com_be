package com.e_com.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: ReviewsDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 07 August 2025
 * @time 4:50:10 PM
 * @version 1.0
 **/

@Data
public class ReviewsDto {
    
    private Integer id;
    
    private Integer userId;
    
    private Integer productId;
    
    private Integer rating;

    private String review;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private Boolean isActive;
    
    private ProductDto product;
}
