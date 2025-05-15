package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.ProductCategoryDto;


@Component
public class ProductCategoryTransformer implements BaseTransformer<ProductCategory, ProductCategoryDto> {

    @Override
    public ProductCategoryDto transform(ProductCategory productCategory) {
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