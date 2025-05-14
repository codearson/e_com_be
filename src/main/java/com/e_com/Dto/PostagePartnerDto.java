package com.e_com.Dto;

import lombok.Data;

/**
 * Title: PostagePartnerDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 14, 2025
 * @version 1.0
 **/

@Data
public class PostagePartnerDto {

	private Integer id;
    
    private String partnerName;
    
    private UserDto userDto;
    
    private Boolean isActive;
	
}
