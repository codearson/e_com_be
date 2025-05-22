package com.e_com.Service;


import java.util.Map;

import com.e_com.Dto.OrderTrackingDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: OrderTrackingService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 19 May 2025
 * @time 5:21:21 pm
 * @version 1.0
 **/

public interface OrderTrackingService {
	

    ResponseDto saveOrderTracking(OrderTrackingDto orderTrackingDto);
    
    ResponseDto updateOrderTracking(OrderTrackingDto orderTrackingDto);
    
    ResponseDto updateOrderTrackingStatus(Integer orderTrackingId, Boolean status);
    
    ResponseDto getAllPageOrderTracking(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    public ResponseDto getAllOrderTracking();
}
