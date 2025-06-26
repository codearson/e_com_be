package com.e_com.Dto;

import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.ProductCategoryDto;

import lombok.Data;

/**
 * Title: ProductSubCategoryDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 8:02:03 AM
 * @version 1.0
 **/

@Data
public class ProductCategoryLevel2Dto {

	private Integer id;
	
	private String name;
	
	private ProductCategoryDto productCategoryDto;
	
	private Boolean isActive;
	
}
