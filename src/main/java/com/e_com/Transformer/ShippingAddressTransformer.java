package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.ShippingAddress;
import com.e_com.Dto.ShippingAddressDto;

/**
 * Title: ShippingAddressTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 14 May 2025
 * @time 23:33:22
 * @version 1.0
 **/

@Component
public class ShippingAddressTransformer implements BaseTransformer<ShippingAddress, ShippingAddressDto>{

	@Autowired
	UserTransfomer userTransformer;
	
	@Override
	public ShippingAddressDto transform(ShippingAddress shippingAddress) {
		ShippingAddressDto shippingAddressDto = null;
		if (shippingAddress != null) {
			shippingAddressDto = new ShippingAddressDto();
			shippingAddressDto.setId(shippingAddress.getId());
			shippingAddressDto.setName(shippingAddress.getName());
			shippingAddressDto.setAddress(shippingAddress.getAddress());
			shippingAddressDto.setMobileNumber(shippingAddress.getMobileNumber());
			shippingAddressDto.setDistrict(shippingAddress.getDistrict());
			shippingAddressDto.setProvince(shippingAddress.getProvince());
			shippingAddressDto.setPostalCode(shippingAddress.getPostalCode());
			if (shippingAddress.getUser() != null) {
				shippingAddressDto.setUserDto(userTransformer.transform(shippingAddress.getUser()));
			}
			shippingAddressDto.setIsPrimary(shippingAddress.getIsPrimary());
			shippingAddressDto.setIsActive(shippingAddress.getIsActive());
		}
		return shippingAddressDto;
	}

	@Override
	public ShippingAddress reverseTransform(ShippingAddressDto shippingAddressDto) {
		ShippingAddress shippingAddress = null;
		if (shippingAddressDto != null) {
			shippingAddress = new ShippingAddress();
			shippingAddress.setId(shippingAddressDto.getId());
			shippingAddress.setName(shippingAddressDto.getName());
			shippingAddress.setAddress(shippingAddressDto.getAddress());
			shippingAddress.setMobileNumber(shippingAddressDto.getMobileNumber());
			shippingAddress.setDistrict(shippingAddressDto.getDistrict());
			shippingAddress.setProvince(shippingAddressDto.getProvince());
			shippingAddress.setPostalCode(shippingAddressDto.getPostalCode());
			if (shippingAddressDto.getUserDto() != null) {
				shippingAddress.setUser(
						userTransformer.reverseTransform(shippingAddressDto.getUserDto()));
			}
			shippingAddress.setIsPrimary(shippingAddressDto.getIsPrimary());
			shippingAddress.setIsActive(shippingAddressDto.getIsActive());
		}
		return shippingAddress;
	}
}
