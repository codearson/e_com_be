package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.OrderTracking;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.OrderTrackingDto;
import com.e_com.Dto.OrdersDto;
import com.e_com.Dto.PaginatedResponseDto;

/**
 * Title: OrderTrackingDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 19 May 2025
 * @time 6:21:17 pm
 * @version 1.0
 **/

public interface OrderTrackingDao extends BaseDao<OrderTracking> {
	
	OrderTrackingDto saveOrderTracking(OrderTrackingDto orderTrackingDto);
    
    OrderTrackingDto updateOrderTracking(OrderTrackingDto orderTrackingDto);
    
    OrderTrackingDto checkOrderTrackingAvailability(Integer orderTrackingId);
    
    PaginatedResponseDto getAllPageOrderTracking(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<OrderTrackingDto> getAllOrderTracking();
	
}
