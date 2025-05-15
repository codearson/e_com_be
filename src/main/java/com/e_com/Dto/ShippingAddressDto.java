package com.e_com.Dto;

import lombok.Data;

/**
 * Title: ShippingAddressDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 14 May 2025
 * @time 23:30:37
 * @version 1.0
 **/

@Data
public class ShippingAddressDto {
	
	private Integer id;
	
	private String address;
	
	private String mobileNumber;
	
	private UserDto userDto;
	
	private Boolean isActive;

}
