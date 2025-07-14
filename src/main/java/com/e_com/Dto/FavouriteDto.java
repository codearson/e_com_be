package com.e_com.Dto;

import lombok.Data;

/**
 * Title: Favourite.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date July 11, 2025
 * @time 6:35:11 PM
 * @version 1.0
 **/

@Data
public class FavouriteDto {
	
    private Integer id;
    
    private ProductDto productDto;
    
    private UserDto userDto;
    
    private Boolean isActive;
} 