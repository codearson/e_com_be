package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.OrdersDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: OrdersService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 22:03:19
 * @version 1.0
 **/

public interface OrdersService {
	
    ResponseDto saveOrders(OrdersDto ordersDto);
    
    ResponseDto updateOrders(OrdersDto ordersDto);
    
    ResponseDto updateOrdersStatus(Integer ordersId, Boolean status);
    
    ResponseDto getAllPageOrders(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    ResponseDto getAllBySearchOrders(String title, String firstName, String partnerName, String type);
    
}