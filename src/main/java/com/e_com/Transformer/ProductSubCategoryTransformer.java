package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.e_com.Domain.ProductSubCategory;
import com.e_com.Dto.ProductSubCategoryDto;



/**
 * Title: ProductSubCategoryTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 8:05:06 AM
 * @version 1.0
 **/

@Component
public class ProductSubCategoryTransformer implements BaseTransformer<ProductSubCategory, ProductSubCategoryDto> {

	@Autowired
	private ProductCategoryTransformer productCategoryTransformer;
	
	 @Override
	    public ProductSubCategoryDto transform(ProductSubCategory productSubCategory) {
	        ProductSubCategoryDto productSubCategoryDto = null;
	        if (productSubCategory != null) {
	            productSubCategoryDto = new ProductSubCategoryDto();
	            productSubCategoryDto.setId(productSubCategory.getId());
	            productSubCategoryDto.setName(productSubCategory.getName());
	            if (productSubCategory.getProductCategory() != null) {
					productSubCategoryDto.setProductCategoryDto(productCategoryTransformer.transform(productSubCategory.getProductCategory()));
				}
	            productSubCategoryDto.setIsActive(productSubCategory.getIsActive());
	        }
	        return productSubCategoryDto;
	    }

	    @Override
	    public ProductSubCategory reverseTransform(ProductSubCategoryDto productSubCategoryDto) {
	        ProductSubCategory productSubCategory = null;
	        if (productSubCategoryDto != null) {
	            productSubCategory = new ProductSubCategory();
	            productSubCategory.setId(productSubCategoryDto.getId());
	            productSubCategory.setName(productSubCategoryDto.getName());
	            if (productSubCategoryDto.getProductCategoryDto() != null) {
					productSubCategory.setProductCategory(productCategoryTransformer.reverseTransform(productSubCategoryDto.getProductCategoryDto()));
				}
	            productSubCategory.setIsActive(productSubCategoryDto.getIsActive());
	        }
	        return productSubCategory;
	    }
}
