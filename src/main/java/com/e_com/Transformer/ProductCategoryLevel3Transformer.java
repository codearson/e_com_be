package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.ProductCategoryLevel3;
import com.e_com.Domain.ProductSubCategory;
import com.e_com.Dto.ProductCategoryLevel3Dto;

/**
 * Title: ProductCategoryLevel3Transformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 25, 2025
 * @time 7:03:08 PM
 * @version 1.0
 **/

@Component
public class ProductCategoryLevel3Transformer implements BaseTransformer<ProductCategoryLevel3, ProductCategoryLevel3Dto> {

    @Autowired
    private ProductSubCategoryTransformer productSubCategoryTransformer;

    @Override
    public ProductCategoryLevel3Dto transform(ProductCategoryLevel3 productCategoryLevel3) {
        ProductCategoryLevel3Dto productCategoryLevel3Dto = null;
        if (productCategoryLevel3 != null) {
            productCategoryLevel3Dto = new ProductCategoryLevel3Dto();
            productCategoryLevel3Dto.setId(productCategoryLevel3.getId());
            productCategoryLevel3Dto.setName(productCategoryLevel3.getName());

            if (productCategoryLevel3.getProductSubCategory() != null) {
                productCategoryLevel3Dto.setProductSubCategoryDto(
                    productSubCategoryTransformer.transform(productCategoryLevel3.getProductSubCategory()));
            }

            productCategoryLevel3Dto.setIsActive(productCategoryLevel3.getIsActive());
        }
        return productCategoryLevel3Dto;
    }

    @Override
    public ProductCategoryLevel3 reverseTransform(ProductCategoryLevel3Dto productCategoryLevel3Dto) {
        ProductCategoryLevel3 productCategoryLevel3 = null;
        if (productCategoryLevel3Dto != null) {
            productCategoryLevel3 = new ProductCategoryLevel3();
            productCategoryLevel3.setId(productCategoryLevel3Dto.getId());
            productCategoryLevel3.setName(productCategoryLevel3Dto.getName());

            if (productCategoryLevel3Dto.getProductSubCategoryDto() != null) {
                productCategoryLevel3.setProductSubCategory(
                    productSubCategoryTransformer.reverseTransform(productCategoryLevel3Dto.getProductSubCategoryDto()));
            }

            productCategoryLevel3.setIsActive(productCategoryLevel3Dto.getIsActive());
        }
        return productCategoryLevel3;
    }
}

