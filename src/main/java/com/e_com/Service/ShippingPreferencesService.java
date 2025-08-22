package com.e_com.Service;

import com.e_com.Dto.ShippingPreferencesDto;
import com.e_com.Dto.ResponseDto;
import java.util.List;

public interface ShippingPreferencesService {
	ResponseDto saveShippingPreferences(ShippingPreferencesDto shippingPreferencesDto);
	
	ResponseDto updateShippingPreferences(ShippingPreferencesDto ShippingPreferencesDto);
	
	ResponseDto updateShippingPreferencesStatus(Integer shippingPreferencesId, Boolean status);
}
