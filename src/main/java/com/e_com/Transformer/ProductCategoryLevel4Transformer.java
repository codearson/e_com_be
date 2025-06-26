package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.ProductCategoryLevel3;
import com.e_com.Domain.ProductCategoryLevel4;
import com.e_com.Dto.ProductCategoryLevel3Dto;
import com.e_com.Dto.ProductCategoryLevel4Dto;

/**
 * Title: ProductCategoryLevel4Transformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 25, 2025
 * @time 8:16:50 PM
 * @version 1.0
 **/

@Component
public class ProductCategoryLevel4Transformer implements BaseTransformer<ProductCategoryLevel4, ProductCategoryLevel4Dto> {

    @Autowired
    private ProductCategoryLevel3Transformer productCategoryLevel3Transformer;

    @Override
    public ProductCategoryLevel4Dto transform(ProductCategoryLevel4 productCategoryLevel4) {
        ProductCategoryLevel4Dto productCategoryLevel4Dto = null;
        if (productCategoryLevel4 != null) {
            productCategoryLevel4Dto = new ProductCategoryLevel4Dto();
            productCategoryLevel4Dto.setId(productCategoryLevel4.getId());
            productCategoryLevel4Dto.setName(productCategoryLevel4.getName());

            if (productCategoryLevel4.getProductCategoryLevel3() != null) {
                productCategoryLevel4Dto.setProductCategoryLevel3Dto(
                		productCategoryLevel3Transformer.transform(productCategoryLevel4.getProductCategoryLevel3()));
            }

            productCategoryLevel4Dto.setIsActive(productCategoryLevel4.getIsActive());
        }
        return productCategoryLevel4Dto;
    }

    @Override
    public ProductCategoryLevel4 reverseTransform(ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        ProductCategoryLevel4 productCategoryLevel4 = null;
        if (productCategoryLevel4Dto != null) {
            productCategoryLevel4 = new ProductCategoryLevel4();
            productCategoryLevel4.setId(productCategoryLevel4Dto.getId());
            productCategoryLevel4.setName(productCategoryLevel4Dto.getName());

            if (productCategoryLevel4Dto.getProductCategoryLevel3Dto() != null) {
                productCategoryLevel4.setProductCategoryLevel3(
                		productCategoryLevel3Transformer.reverseTransform(productCategoryLevel4Dto.getProductCategoryLevel3Dto()));
            }

            productCategoryLevel4.setIsActive(productCategoryLevel4Dto.getIsActive());
        }
        return productCategoryLevel4;
    }
}

