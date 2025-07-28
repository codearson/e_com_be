package com.e_com.Service.BL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.OrdersDao;
import com.e_com.Dto.OrdersDto;
import com.e_com.Dto.PaginatedResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrdersServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 22:03:59
 * @version 1.0
 **/

@Slf4j
@Service
public class OrdersServiceBL {

    @Autowired
    private OrdersDao ordersDao;

    public OrdersDto saveOrders(OrdersDto ordersDto) {
        log.info("OrdersServiceBL.saveOrders() invoked.");
        if (ordersDto != null) {
            // Set createdAt and updatedAt for new order
            ordersDto.setCreatedAt(LocalDateTime.now());
            ordersDto.setUpdatedAt(ordersDto.getCreatedAt());
        }
        return ordersDao.saveOrders(ordersDto);
    }

    public OrdersDto updateOrders(OrdersDto ordersDto) {
        log.info("OrdersServiceBL.updateOrders() invoked.");
        OrdersDto existingDto = ordersDao.checkOrdersAvailability(ordersDto.getId());
        if (existingDto == null) {
            log.info("Orders with id {} not found.", ordersDto.getId());
            return null;
        }
        if (ordersDto != null) {
            // Preserve existing createdAt, set new updatedAt
            ordersDto.setCreatedAt(existingDto.getCreatedAt());
            ordersDto.setUpdatedAt(LocalDateTime.now());
        }
        return ordersDao.updateOrders(ordersDto);
    }

    public OrdersDto updateOrdersStatus(Integer ordersId, Boolean status) {
        log.info("OrdersServiceBL.updateOrdersStatus() invoked with ordersId: {}, status: {}", ordersId, status);
        OrdersDto ordersDto = ordersDao.checkOrdersAvailability(ordersId);
        if (ordersDto != null) {
            ordersDto.setIsActive(status);
            // Set updatedAt for status update
            ordersDto.setUpdatedAt(LocalDateTime.now());
            return ordersDao.updateOrders(ordersDto);
        } else {
            log.info("Orders with id {} not found.", ordersId);
            return null;
        }
    }

    public PaginatedResponseDto getAllPageOrders(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("OrdersServiceBL.getAllPageOrders() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return ordersDao.getAllPageOrders(pageNumber, pageSize, status, searchParameters);
    }

    public List<OrdersDto> getAllBySearchOrders(String title, String firstName, String partnerName, String type) {
        log.info("OrdersServiceBL.getAllBySearchOrders() invoked with title: {}, firstName: {}, partnerName: {}, type: {}", 
                 title, firstName, partnerName, type);
        return ordersDao.getAllBySearchOrders(title, firstName, partnerName, type);
    }

    public OrdersDto getOrderById(Integer ordersId) {
        log.info("OrdersServiceBL.getOrderById() invoked with ordersId: {}", ordersId);
        return ordersDao.checkOrdersAvailability(ordersId);
    }
}
