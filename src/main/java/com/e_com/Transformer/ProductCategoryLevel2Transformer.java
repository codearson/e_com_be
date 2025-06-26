package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.ProductCategoryLevel2;
import com.e_com.Dto.ProductCategoryLevel2Dto;



/**
 * Title: ProductSubCategoryTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 8:05:06 AM
 * @version 1.0
 **/

@Component
public class ProductCategoryLevel2Transformer implements BaseTransformer<ProductCategoryLevel2, ProductCategoryLevel2Dto> {

	@Autowired
	private ProductCategoryLevel1Transformer productCategoryLevel1Transformer;
	
	 @Override
	    public ProductCategoryLevel2Dto transform(ProductCategoryLevel2 productCategoryLevel2) {
	        ProductCategoryLevel2Dto productCategoryLevel2Dto = null;
	        if (productCategoryLevel2 != null) {
	        	productCategoryLevel2Dto = new ProductCategoryLevel2Dto();
	        	productCategoryLevel2Dto.setId(productCategoryLevel2.getId());
	        	productCategoryLevel2Dto.setName(productCategoryLevel2.getName());
	            if (productCategoryLevel2.getProductCategoryLevel1() != null) {
	            	productCategoryLevel2Dto.setProductCategoryLevel1Dto(productCategoryLevel1Transformer.transform(productCategoryLevel2.getProductCategoryLevel1()));
				}
	            productCategoryLevel2Dto.setIsActive(productCategoryLevel2.getIsActive());
	        }
	        return productCategoryLevel2Dto;
	    }

	    @Override
	    public ProductCategoryLevel2 reverseTransform(ProductCategoryLevel2Dto productCategoryLevel2Dto) {
	    	ProductCategoryLevel2 productCategoryLevel2 = null;
	        if (productCategoryLevel2Dto != null) {
	        	productCategoryLevel2 = new ProductCategoryLevel2();
	        	productCategoryLevel2.setId(productCategoryLevel2Dto.getId());
	        	productCategoryLevel2.setName(productCategoryLevel2Dto.getName());
	            if (productCategoryLevel2Dto.getProductCategoryLevel1Dto() != null) {
	            	productCategoryLevel2.setProductCategoryLevel1(productCategoryLevel1Transformer.reverseTransform(productCategoryLevel2Dto.getProductCategoryLevel1Dto()));
				}
	            productCategoryLevel2.setIsActive(productCategoryLevel2Dto.getIsActive());
	        }
	        return productCategoryLevel2;
	    }
}
