package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.OrderTracking;
import com.e_com.Domain.Orders;
import com.e_com.Dto.OrderTrackingDto;
import com.e_com.Dto.OrdersDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrderTrackingTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 17 May 2025
 * @time 2:42:35 pm
 * @version 1.0
 **/
@Slf4j
@Component
public class OrderTrackingTransformer implements BaseTransformer<OrderTracking, OrderTrackingDto> {
		
		@Autowired
		private PostagePartnerTransformer postagePartnerTransformer;
		
		@Autowired
		private BranchTransformer branchTransformer;
		
		@Autowired
		private UserTransfomer userTransformer;
		
		@Autowired
		private OrdersTransformer ordersTransformer;
		
		@Override
		public OrderTrackingDto transform(OrderTracking orderTracking) {
	        log.debug("Transforming OrderTracking to OrderTrackingDto for id: {}", orderTracking != null ? orderTracking.getId() : null);
	        OrderTrackingDto orderTrackingDto = null;
	        if (orderTracking != null) {
	            orderTrackingDto = new OrderTrackingDto();
	            orderTrackingDto.setId(orderTracking.getId());
	            if (orderTracking.getPostagePartner() != null) {
	                orderTrackingDto.setPostagePartnerDto(postagePartnerTransformer.transform(orderTracking.getPostagePartner()));
	            }
	            if (orderTracking.getBranch() != null) {
	                orderTrackingDto.setBranchDto(branchTransformer.transform(orderTracking.getBranch()));
	            }
	            if (orderTracking.getUser() != null) {
	                orderTrackingDto.setUserDto(userTransformer.transform(orderTracking.getUser()));
	            }
	            if (orderTracking.getOrders() != null) {
	            	orderTrackingDto.setOrdersDto(ordersTransformer.transform(orderTracking.getOrders()));
	            }
	            
	            orderTrackingDto.setTrackingId(orderTracking.getTrackingId());
	            orderTrackingDto.setIsActive(orderTracking.getIsActive());
	        }
	        return orderTrackingDto;
	    }
		
		@Override
		public OrderTracking reverseTransform(OrderTrackingDto orderTrackingDto) {
	        log.debug("Reverse transforming OrderTrackingDto to OrderTracking for id: {}", orderTrackingDto != null ? orderTrackingDto.getId() : null);
	        OrderTracking orderTracking = null;
	        if (orderTrackingDto != null) {
	            orderTracking = new OrderTracking();
	            orderTracking.setId(orderTrackingDto.getId());
	            if (orderTrackingDto.getPostagePartnerDto() != null) {
	                orderTracking.setPostagePartner(postagePartnerTransformer.reverseTransform(orderTrackingDto.getPostagePartnerDto()));
	            }
	            if (orderTrackingDto.getBranchDto() != null) {
	                orderTracking.setBranch(branchTransformer.reverseTransform(orderTrackingDto.getBranchDto()));
	            }
	            if (orderTrackingDto.getUserDto() != null) {
	                orderTracking.setUser(userTransformer.reverseTransform(orderTrackingDto.getUserDto()));
	            }
	            if (orderTrackingDto.getOrdersDto() != null) {
	                orderTracking.setOrders(ordersTransformer.reverseTransform(orderTrackingDto.getOrdersDto()));
	            }
	           
	            orderTracking.setTrackingId(orderTrackingDto.getTrackingId());
	            orderTracking.setIsActive(orderTrackingDto.getIsActive());
	        }
	        return orderTracking;
	    }
		
}
