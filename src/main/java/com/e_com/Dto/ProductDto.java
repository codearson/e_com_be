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
	
	private ProductCategoryLevel1Dto productCategoryLevel1Dto;
	
	private BrandDto brandDto;
	
	private ConditionsDto conditionsDto;
	
	private StatusDto statusDto;
	
	private String title;
	
	private String description;
	
	private String size;
	
	private String color;
	
	private Double price;
	
	private Integer quentity;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private String imageUrl;
	
	private Boolean isActive;

}
