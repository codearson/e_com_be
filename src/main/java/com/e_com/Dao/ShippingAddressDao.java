package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.ShippingAddress;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ShippingAddressDto;

/**
 * Title: ShippingAddressDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 15 May 2025
 * @time 19:00:58
 * @version 1.0
 **/

public interface ShippingAddressDao extends BaseDao<ShippingAddress> {
	
    ShippingAddressDto saveShippingAddress(ShippingAddressDto shippingAddressDto);
    
    ShippingAddressDto updateShippingAddress(ShippingAddressDto shippingAddressDto);
    
    ShippingAddressDto checkShippingAddressAvailability(Integer shippingAddressId);
    
    PaginatedResponseDto getAllPageShippingAddress(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<ShippingAddressDto> getAllShippingAddress(String address);
}