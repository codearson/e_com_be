package com.e_com.Dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Title: ProductDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 18:33:09
 * @version 1.0
 **/

@Data
public class ProductDto {
	
	private Integer id;
	
	private ProductCategoryDto productCategoryDto;
	
	private BrandDto brandDto;
	
	private ConditionsDto conditionsDto;
	
	private StatusDto statusDto;
	
	private String title;
	
	private String description;
	
	private String size;
	
	private String color;
	
	private Double price;
	
	private Integer quentity;
	
	private UserDto userDto;
	
	private Integer userId;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private String imageUrl;
	
	private Boolean isActive;

	private java.util.List<ProductCategoryDto> categoryChain;

}
