package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.Orders;
import com.e_com.Dto.OrdersDto;
import com.e_com.Dto.PaginatedResponseDto;

/**
 * Title: OrdersDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 22:04:19
 * @version 1.0
 **/

public interface OrdersDao extends BaseDao<Orders> {
	
    OrdersDto saveOrders(OrdersDto ordersDto);
    
    OrdersDto updateOrders(OrdersDto ordersDto);
    
    OrdersDto checkOrdersAvailability(Integer ordersId);
    
    PaginatedResponseDto getAllPageOrders(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<OrdersDto> getAllBySearchOrders(String title, String firstName, String partnerName, String type);
    
}