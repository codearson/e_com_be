package com.e_com.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.OrderTrackingDao;
import com.e_com.Domain.Conditions;
import com.e_com.Domain.OrderTracking;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.OrderTrackingDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.OrderTrackingTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrderTrackingDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 19 May 2025
 * @time 7:04:29 pm
 * @version 1.0
 **/
@Slf4j
@Repository
public class OrderTrackingDaoImpl extends BaseDaoImpl<OrderTracking> implements OrderTrackingDao{
	
	@Autowired
    OrderTrackingTransformer orderTrackingTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Transactional
    public OrderTrackingDto saveOrderTracking(OrderTrackingDto orderTrackingDto) {
        log.info("OrderTrackingDaoImpl.saveOrderTracking() invoked.");
        try {
            OrderTracking orderTracking = orderTrackingTransformer.reverseTransform(orderTrackingDto);
            OrderTracking savedOrderTracking = saveOrUpdate(orderTracking);
            return orderTrackingTransformer.transform(savedOrderTracking);
        } catch (Exception e) {
            log.error("Error saving OrdersTracking: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public OrderTrackingDto updateOrderTracking(OrderTrackingDto orderTrackingDto) {
        log.info("OrderTrackingDaoImpl.updateOrderTracking() invoked.");
        try {
            OrderTracking orderTracking = orderTrackingTransformer.reverseTransform(orderTrackingDto);
            OrderTracking updatedOrderTracking = saveOrUpdate(orderTracking);
            return orderTrackingTransformer.transform(updatedOrderTracking);
        } catch (Exception e) {
            log.error("Error updating OrderTracking: {}", e.getMessage());
            throw e;
        }
    }
    
    @Transactional
    public OrderTrackingDto checkOrderTrackingAvailability(Integer orderTrackingId) {
        log.info("OrderTrackingDaoImpl.checkOrderTrackingAvailability() invoked with orderTrackingId: {}", orderTrackingId);
        Criteria criteria = getCurrentSession().createCriteria(OrderTracking.class, "orderTracking");
        criteria.add(Restrictions.eq("id", orderTrackingId));
        OrderTracking orderTracking = (OrderTracking) criteria.uniqueResult();
        return orderTracking != null ? orderTrackingTransformer.transform(orderTracking) : null;
    }
    
    @Override
    @Transactional
    public PaginatedResponseDto getAllPageOrderTracking(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("OrderTrackingDaoImpl.getAllPageOrderTracking() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<OrderTracking> orderTrackingList = null;

        // Build the count query with status filter
        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM order_Tracking");
        if (status != null) {
            countString.append(" WHERE is_active = ?");
        }
        int count = status != null 
            ? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
            : jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(OrderTracking.class, "orderTracking");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        orderTrackingList = criteria.list();

        if (orderTrackingList != null && !orderTrackingList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(orderTrackingList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(orderTrackingList.stream()
                .map(orderTrackingTransformer::transform)
                .collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
    @Override
	@Transactional
	public List<OrderTrackingDto> getAllOrderTracking() {
		log.info("OrderTrackingDaoImpl.getAllOrderTracking() invoked");
		Criteria criteria = getCurrentSession().createCriteria(OrderTracking.class, "orderTracking");
		criteria.addOrder(Order.asc("id"));
		List<OrderTrackingDto> orderTrackingDtoList = null;
		List<OrderTracking> orderTrackingList = (List<OrderTracking>) criteria.list();
		if (orderTrackingList != null && !orderTrackingList.isEmpty()) {
			orderTrackingDtoList = new ArrayList<>();
			for (OrderTracking orderTracking : orderTrackingList) {
				orderTrackingDtoList.add(orderTrackingTransformer.transform(orderTracking));
			}
		}
		return orderTrackingDtoList;
	}
	
}
