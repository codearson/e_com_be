package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.OrderTrackingDao;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.OrderTrackingDto;
import com.e_com.Dto.PaginatedResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrderTrackingServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 19 May 2025
 * @time 5:44:50 pm
 * @version 1.0
 **/
@Slf4j
@Service
public class OrderTrackingServiceBL {
	
	@Autowired
    private OrderTrackingDao orderTrackingDao;

    public OrderTrackingDto saveOrderTracking(OrderTrackingDto orderTrackingDto) {
        log.info("OrderTrackingServiceBL.saveOrderTracking() invoked.");
        return orderTrackingDao.saveOrderTracking(orderTrackingDto);
    }
    
    public OrderTrackingDto updateOrderTracking(OrderTrackingDto orderTrackingDto) {
        log.info("OrderTrackingServiceBL.updateOrderTracking() invoked.");
        OrderTrackingDto existingDto = orderTrackingDao.checkOrderTrackingAvailability(orderTrackingDto.getId());
        if (existingDto == null) {
            log.info("OrderTracking with id {} not found.", orderTrackingDto.getId());
            return null;
        }
        return orderTrackingDao.updateOrderTracking(orderTrackingDto);
      
    }
    
    public OrderTrackingDto updateOrderTrackingStatus(Integer orderTrackingId, Boolean status) {
        log.info("OrderTrackingServiceBL.updateOrderTrackingStatus() invoked with orderTrackingId: {}, status: {}", orderTrackingId, status);
        OrderTrackingDto orderTrackingDto = orderTrackingDao.checkOrderTrackingAvailability(orderTrackingId);
        if (orderTrackingDto != null) {
            orderTrackingDto.setIsActive(status);
             orderTrackingDao.updateOrderTracking(orderTrackingDto);
             return orderTrackingDao.updateOrderTracking(orderTrackingDto);
        } else {
            log.info("OrderTracking with id {} not found.", orderTrackingId);
            return null;
        }
    }
    
    public PaginatedResponseDto getAllPageOrderTracking(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("OrderTrackingServiceBL.getAllPageOrderTracking() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return orderTrackingDao.getAllPageOrderTracking(pageNumber, pageSize, status, searchParameters);
    }
    
    public List<OrderTrackingDto> getAllOrderTracking() {
		log.info("OrderTrackingServiceBL.getAllOrderTracking()invoked");
		return orderTrackingDao.getAllOrderTracking();
	}

}
