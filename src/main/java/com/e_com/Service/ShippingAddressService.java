package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.ShippingAddressDto;

/**
 * Title: ShippingAddressService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 15 May 2025
 * @time 18:59:52
 * @version 1.0
 **/

public interface ShippingAddressService {
	
	ResponseDto saveShippingAddress(ShippingAddressDto brandDto);
    
    ResponseDto updateShippingAddress(ShippingAddressDto brandDto);
    
    ResponseDto updateShippingAddressStatus(Integer shippingAddressId, Boolean status);
    
    ResponseDto getAllPageShippingAddress(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    ResponseDto getAllShippingAddress(String address);

}
