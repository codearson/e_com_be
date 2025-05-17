package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.ProductCategoryDto;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ProductCategoryTransformer implements BaseTransformer<ProductCategory, ProductCategoryDto> {

    @Override
    public ProductCategoryDto transform(ProductCategory productCategory) {
        log.debug("Transforming ProductCategory to ProductCategoryDto for id: {}", 
                 productCategory != null ? productCategory.getId() : null);
        ProductCategoryDto productCategoryDto = null;
        if (productCategory != null) {
            productCategoryDto = new ProductCategoryDto();
            productCategoryDto.setId(productCategory.getId());
            productCategoryDto.setName(productCategory.getName());
            productCategoryDto.setIsActive(productCategory.getIsActive());
        }
        return productCategoryDto;
    }

    @Override
    public ProductCategory reverseTransform(ProductCategoryDto productCategoryDto) {
        log.debug("Reverse transforming ProductCategoryDto to ProductCategory for id: {}", 
                 productCategoryDto != null ? productCategoryDto.getId() : null);
        ProductCategory productCategory = null;
        if (productCategoryDto != null) {
            productCategory = new ProductCategory();
            productCategory.setId(productCategoryDto.getId());
            productCategory.setName(productCategoryDto.getName());
            productCategory.setIsActive(productCategoryDto.getIsActive());
        }
        return productCategory;
    }
}