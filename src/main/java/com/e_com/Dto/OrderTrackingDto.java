package com.e_com.Dto;

import com.e_com.Domain.Branch;
import com.e_com.Domain.Orders;
import com.e_com.Domain.PostagePartner;
import com.e_com.Domain.User;

import lombok.Data;

/**
 * Title: OrderTrackingDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 16 May 2025
 * @time 11:37:28 pm
 * @version 1.0
 **/
@Data
public class OrderTrackingDto {
	
	private Integer id;
	
	private PostagePartnerDto postagePartnerDto;
	
	private BranchDto branchDto;
	
	private UserDto userDto;
	
	private OrdersDto ordersDto;
	
	private String trackingId;
	
	private Boolean isActive;
	
}
