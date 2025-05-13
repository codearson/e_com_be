package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.ProductCategoryDto;



@Component
public class ProductCategoryTransformer {

    // Convert Domain to DTO
    public ProductCategoryDto transform(ProductCategory productCategory) {
        ProductCategoryDto dto = null;
        if (productCategory != null) {
            dto = new ProductCategoryDto();
            dto.setId(productCategory.getId());
            dto.setName(productCategory.getName());
            dto.setIsActive(productCategory.getIsActive());
        }
        return dto;
    }

    // Convert DTO to Domain
    public ProductCategory reverseTransform(ProductCategoryDto dto) {
        ProductCategory productCategory = null;
        if (dto != null) {
            productCategory = new ProductCategory();
            productCategory.setId(dto.getId());
            productCategory.setName(dto.getName());
            productCategory.setIsActive(dto.getIsActive());
        }
        return productCategory;
    }
}
