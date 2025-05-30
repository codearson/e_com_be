package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.Product;
import com.e_com.Domain.ProductImage;
import com.e_com.Dto.ProductImageDto;

/**
 * Title: ProductImage.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 28, 2025
 * @time 7:42:26 PM
 * @version 1.0
 **/

@Component
public class ProductImageTransformer implements BaseTransformer<ProductImage, ProductImageDto> {

	@Autowired
	ProductTransformer productTransformer;
	
    @Override
    public ProductImageDto transform(ProductImage productImage) {
        ProductImageDto productImageDto = null;
        if (productImage != null) {
            productImageDto = new ProductImageDto();
            productImageDto.setId(productImage.getId());
            productImageDto.setUrl(productImage.getUrl());
            if (productImage.getProduct() != null) {
                productImageDto.setProductDto(productTransformer.transform(productImage.getProduct()));
            }
            productImageDto.setIsActive(productImage.getIsActive());
        }
        return productImageDto;
    }

    @Override
    public ProductImage reverseTransform(ProductImageDto productImageDto) {
        ProductImage productImage = null;
        if (productImageDto != null) {
            productImage = new ProductImage();
            productImage.setId(productImageDto.getId());
            productImage.setUrl(productImageDto.getUrl());
        	if (productImageDto.getProductDto() != null) {
        		productImage.setProduct(productTransformer.reverseTransform(productImageDto.getProductDto()));
			}
            
            productImage.setIsActive(productImageDto.getIsActive());
        }
        return productImage;
    }
}
