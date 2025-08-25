package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.Conditions;
import com.e_com.Domain.ShippingPreferences;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.ShippingPreferencesDto;
import com.e_com.Dto.UserDto;

@Component
public class ShippingPreferencesTransformer implements BaseTransformer<ShippingPreferences, ShippingPreferencesDto> {

	@Override
	public ShippingPreferencesDto transform(ShippingPreferences shippingPreferences) {
	    ShippingPreferencesDto shippingPreferencesDto = null;
	    if (shippingPreferences != null) {
	        shippingPreferencesDto = new ShippingPreferencesDto();
	        shippingPreferencesDto.setId(shippingPreferences.getId());
	        shippingPreferencesDto.setShippingName(shippingPreferences.getShippingName());
	        shippingPreferencesDto.setCourierService(shippingPreferences.getCourierService());
	        shippingPreferencesDto.setPackageWeight(shippingPreferences.getPackageWeight());
	        shippingPreferencesDto.setPrice(shippingPreferences.getPrice());
	        shippingPreferencesDto.setCreatedAt(shippingPreferences.getCreatedAt());
	        shippingPreferencesDto.setUpdatedAt(shippingPreferences.getUpdatedAt());

	        if (shippingPreferences.getUser() != null) {
	            UserDto userDto = new UserDto();
	            userDto.setId(shippingPreferences.getUser().getId());
	            shippingPreferencesDto.setUserDto(userDto);
	        }

	        shippingPreferencesDto.setIsActive(shippingPreferences.getIsActive());
	    }
	    return shippingPreferencesDto;
	}

	@Override
	public ShippingPreferences reverseTransform(ShippingPreferencesDto shippingPreferencesDto) {
	    ShippingPreferences shippingPreferences = null;
	    if (shippingPreferencesDto != null) {
	        shippingPreferences = new ShippingPreferences();
	        shippingPreferences.setId(shippingPreferencesDto.getId());
	        shippingPreferences.setShippingName(shippingPreferencesDto.getShippingName());
	        shippingPreferences.setCourierService(shippingPreferencesDto.getCourierService());
	        shippingPreferences.setPackageWeight(shippingPreferencesDto.getPackageWeight());
	        shippingPreferences.setPrice(shippingPreferencesDto.getPrice());
	        shippingPreferences.setIsActive(shippingPreferencesDto.getIsActive());

	        // Handle user mapping
	        if (shippingPreferencesDto.getUserDto() != null) {
	            shippingPreferences.setUser(
	                new com.e_com.Domain.User() {{
	                    setId(shippingPreferencesDto.getUserDto().getId());     
	                }}
	            );
	        }
	    }
	    return shippingPreferences;
	}
}
