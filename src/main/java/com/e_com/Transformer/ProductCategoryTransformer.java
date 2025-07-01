package com.e_com.Transformer;

import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.ProductCategoryDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductCategoryTransformer {
    public ProductCategoryDto transform(ProductCategory entity) {
        return transform(entity, false);
    }

    public ProductCategoryDto transform(ProductCategory entity, boolean includeChildren) {
        if (entity == null) return null;
        ProductCategoryDto dto = new ProductCategoryDto();
        dto.setId(entity.getId());
        dto.setIsActive(entity.getIsActive());
        dto.setLevel(entity.getLevel());
        dto.setName(entity.getName());
        dto.setParentId(entity.getParent() != null ? entity.getParent().getId() : null);
        if (includeChildren && entity.getChildren() != null) {
            List<ProductCategoryDto> children = entity.getChildren().stream()
                .map(child -> transform(child, true))
                .collect(Collectors.toList());
            dto.setChildren(children);
        }
        return dto;
    }

    public ProductCategory reverseTransform(ProductCategoryDto dto) {
        if (dto == null) return null;
        ProductCategory entity = new ProductCategory();
        entity.setId(dto.getId());
        entity.setIsActive(dto.getIsActive());
        entity.setLevel(dto.getLevel());
        entity.setName(dto.getName());
        // Parent assignment should be handled in the service/DAO layer
        return entity;
    }
} 