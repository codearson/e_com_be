package com.e_com.Dto;

import lombok.Data;

/**
 * Title: ProductImage.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 28, 2025
 * @time 7:38:21 PM
 * @version 1.0
 **/

@Data
public class ProductImageDto {
    private Integer id;
    private String url;
    private ProductDto productDto;
    private Boolean isActive;
}

