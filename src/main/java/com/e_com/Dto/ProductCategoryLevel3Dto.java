package com.e_com.Dto;

import lombok.Data;

/**
 * Title: ProductCategoryLevel3Dto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 25, 2025
 * @time 7:00:49 PM
 * @version 1.0
 **/

@Data
public class ProductCategoryLevel3Dto {

	private Integer id;
	
	private String name;
	
	private ProductCategoryLevel2Dto productCategoryLevel2Dto;
	
	private Boolean isActive;
	
}
