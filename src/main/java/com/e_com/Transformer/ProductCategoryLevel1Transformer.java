package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.ProductCategoryLevel1;
import com.e_com.Dto.ProductCategoryLevel1Dto;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ProductCategoryLevel1Transformer implements BaseTransformer<ProductCategoryLevel1, ProductCategoryLevel1Dto> {

    @Override
    public ProductCategoryLevel1Dto transform(ProductCategoryLevel1 productCategoryLevel1) {
        log.debug("Transforming ProductCategoryLevel1 to ProductCategoryLevel1Dto for id: {}", 
                 productCategoryLevel1 != null ? productCategoryLevel1.getId() : null);
        ProductCategoryLevel1Dto productCategoryLevel1Dto = null;
        if (productCategoryLevel1 != null) {
            productCategoryLevel1Dto = new ProductCategoryLevel1Dto();
            productCategoryLevel1Dto.setId(productCategoryLevel1.getId());
            productCategoryLevel1Dto.setName(productCategoryLevel1.getName());
            productCategoryLevel1Dto.setIsActive(productCategoryLevel1.getIsActive());
        }
        return productCategoryLevel1Dto;
    }

    @Override
    public ProductCategoryLevel1 reverseTransform(ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.debug("Reverse transforming ProductCategoryLevel1Dto to ProductCategoryLevel1 for id: {}", 
                 productCategoryLevel1Dto != null ? productCategoryLevel1Dto.getId() : null);
        ProductCategoryLevel1 productCategoryLevel1 = null;
        if (productCategoryLevel1Dto != null) {
            productCategoryLevel1 = new ProductCategoryLevel1();
            productCategoryLevel1.setId(productCategoryLevel1Dto.getId());
            productCategoryLevel1.setName(productCategoryLevel1Dto.getName());
            productCategoryLevel1.setIsActive(productCategoryLevel1Dto.getIsActive());
        }
        return productCategoryLevel1;
    }
}