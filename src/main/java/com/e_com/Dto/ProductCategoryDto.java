package com.e_com.Dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductCategoryDto {
	
    private Long id;
    
    private Boolean isActive;
    
    private Integer level;
    
    private String name;
    
    private Long parentId;
    
    private List<ProductCategoryDto> children;
} 