package com.e_com.Dto;

import lombok.Data;

/**
 * Title: ProductCategoryLevel4Dto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 25, 2025
 * @time 8:14:49 PM
 * @version 1.0
 **/

@Data
public class ProductCategoryLevel4Dto {

	private Integer id;
	
	private String name;
	
	private ProductCategoryLevel3Dto productCategoryLevel3Dto;
	
	private Boolean isActive;
	
}