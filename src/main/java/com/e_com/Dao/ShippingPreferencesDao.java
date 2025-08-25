package com.e_com.Dao;

import com.e_com.Domain.ShippingPreferences;
import com.e_com.Dto.ProductDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.ShippingPreferencesDto;

import java.util.List;

public interface ShippingPreferencesDao extends BaseDao<ShippingPreferences> {
	ShippingPreferencesDto saveShippingPreferences(ShippingPreferencesDto shippingPreferencesDto);
	
	ShippingPreferencesDto updateShippingPreferences(ShippingPreferencesDto shippingPreferencesDto);
	
	ShippingPreferencesDto checkShippingPreferencesAvailability(Integer shippingPreferencesId);

	List<ShippingPreferencesDto> findByUserId(Integer shippingPreferencesId);
   
}
