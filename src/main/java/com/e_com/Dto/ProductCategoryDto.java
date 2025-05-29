package com.e_com.Dto;

import lombok.Data;


@Data
public class ProductCategoryDto {

    private Integer id;

    private String name;
    
    private Integer parentId;
    
    private Integer level;

    private Boolean isActive;
}