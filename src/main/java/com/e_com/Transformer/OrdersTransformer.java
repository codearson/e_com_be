package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.Orders;
import com.e_com.Dto.OrdersDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrdersTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 21:59:50
 * @version 1.0
 **/

@Slf4j
@Component
public class OrdersTransformer implements BaseTransformer<Orders, OrdersDto> {

    @Autowired
    private ProductTransformer productTransformer;

    @Autowired
    private UserTransfomer userTransformer;

    @Autowired
    private PostagePartnerTransformer postagePartnerTransformer;

    @Autowired
    private ShippingAddressTransformer shippingAddressTransformer;

    @Autowired
    private StatusTransformer statusTransformer;

    @Override
    public OrdersDto transform(Orders orders) {
        log.debug("Transforming Orders to OrdersDto for id: {}", orders != null ? orders.getId() : null);
        OrdersDto ordersDto = null;
        if (orders != null) {
            ordersDto = new OrdersDto();
            ordersDto.setId(orders.getId());
            if (orders.getProduct() != null) {
                ordersDto.setProductDto(productTransformer.transform(orders.getProduct()));
            }
            if (orders.getUser() != null) {
                ordersDto.setUserDto(userTransformer.transform(orders.getUser()));
            }
            if (orders.getPostagePartner() != null) {
                ordersDto.setPostagePartnerDto(postagePartnerTransformer.transform(orders.getPostagePartner()));
            }
            if (orders.getShippingAddress() != null) {
                ordersDto.setShippingAddressDto(shippingAddressTransformer.transform(orders.getShippingAddress()));
            }
            if (orders.getStatus() != null) {
                ordersDto.setStatusDto(statusTransformer.transform(orders.getStatus()));
            }
            ordersDto.setCreatedAt(orders.getCreatedAt());
            ordersDto.setUpdatedAt(orders.getUpdatedAt());
            ordersDto.setEstimateDeliveryDate(orders.getEstimateDeliveryDate());
            ordersDto.setQuantity(orders.getQuantity());
            ordersDto.setIsActive(orders.getIsActive());
        }
        return ordersDto;
    }

    @Override
    public Orders reverseTransform(OrdersDto ordersDto) {
        log.debug("Reverse transforming OrdersDto to Orders for id: {}", ordersDto != null ? ordersDto.getId() : null);
        Orders orders = null;
        if (ordersDto != null) {
            orders = new Orders();
            orders.setId(ordersDto.getId());
            if (ordersDto.getProductDto() != null) {
                orders.setProduct(productTransformer.reverseTransform(ordersDto.getProductDto()));
            }
            if (ordersDto.getUserDto() != null) {
                orders.setUser(userTransformer.reverseTransform(ordersDto.getUserDto()));
            }
            if (ordersDto.getPostagePartnerDto() != null) {
                orders.setPostagePartner(postagePartnerTransformer.reverseTransform(ordersDto.getPostagePartnerDto()));
            }
            if (ordersDto.getShippingAddressDto() != null) {
                orders.setShippingAddress(shippingAddressTransformer.reverseTransform(ordersDto.getShippingAddressDto()));
            }
            if (ordersDto.getStatusDto() != null) {
                orders.setStatus(statusTransformer.reverseTransform(ordersDto.getStatusDto()));
            }
            orders.setCreatedAt(ordersDto.getCreatedAt());
            orders.setUpdatedAt(ordersDto.getUpdatedAt());
            orders.setEstimateDeliveryDate(ordersDto.getEstimateDeliveryDate());
            orders.setQuantity(ordersDto.getQuantity());
            orders.setIsActive(ordersDto.getIsActive());
        }
        return orders;
    }
}