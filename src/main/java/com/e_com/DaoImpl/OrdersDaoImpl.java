package com.e_com.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.OrdersDao;
import com.e_com.Domain.Orders;
import com.e_com.Dto.OrdersDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.OrdersTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: OrdersDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 22:04:39
 * @version 1.0
 **/

@Slf4j
@Repository
public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements OrdersDao {

    @Autowired
    OrdersTransformer ordersTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public OrdersDto saveOrders(OrdersDto ordersDto) {
        log.info("OrdersDaoImpl.saveOrders() invoked.");
        try {
            Orders orders = ordersTransformer.reverseTransform(ordersDto);
            Orders savedOrders = saveOrUpdate(orders);
            return ordersTransformer.transform(savedOrders);
        } catch (Exception e) {
            log.error("Error saving Orders: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public OrdersDto updateOrders(OrdersDto ordersDto) {
        log.info("OrdersDaoImpl.updateOrders() invoked.");
        try {
            Orders orders = ordersTransformer.reverseTransform(ordersDto);
            Orders updatedOrders = saveOrUpdate(orders);
            return ordersTransformer.transform(updatedOrders);
        } catch (Exception e) {
            log.error("Error updating Orders: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public OrdersDto checkOrdersAvailability(Integer ordersId) {
        log.info("OrdersDaoImpl.checkOrdersAvailability() invoked with ordersId: {}", ordersId);
        Criteria criteria = getCurrentSession().createCriteria(Orders.class, "orders");
        criteria.add(Restrictions.eq("id", ordersId));
        Orders orders = (Orders) criteria.uniqueResult();
        return orders != null ? ordersTransformer.transform(orders) : null;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageOrders(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("OrdersDaoImpl.getAllPageOrders() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<Orders> ordersList = null;

        // Build the count query with status filter
        StringBuilder countString = new StringBuilder("SELECT COUNT(*) FROM orders");
        if (status != null) {
            countString.append(" WHERE is_active = ?");
        }
        int count = status != null 
            ? jdbcTemplate.queryForObject(countString.toString(), new Object[]{status}, Integer.class)
            : jdbcTemplate.queryForObject(countString.toString(), Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(Orders.class, "orders");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        ordersList = criteria.list();

        if (ordersList != null && !ordersList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(ordersList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(ordersList.stream()
                .map(ordersTransformer::transform)
                .collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }

    @Override
    @Transactional
    public List<OrdersDto> getAllBySearchOrders(String title, String firstName, String partnerName, String type) {
        log.info("OrdersDaoImpl.getAllBySearchOrders() invoked with title: {}, firstName: {}, partnerName: {}, type: {}", 
                 title, firstName, partnerName, type);
        Criteria criteria = getCurrentSession().createCriteria(Orders.class, "orders");

        // Join related entities
        criteria.createAlias("product", "product", JoinType.INNER_JOIN);
        criteria.createAlias("user", "user", JoinType.INNER_JOIN);
        criteria.createAlias("postagePartner", "postagePartner", JoinType.INNER_JOIN);
        criteria.createAlias("status", "status", JoinType.INNER_JOIN);

        // Add search filters if provided
        if (title != null && !title.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("product.title", "%" + title.trim() + "%"));
        }
        if (firstName != null && !firstName.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("user.firstName", "%" + firstName.trim() + "%"));
        }
        if (partnerName != null && !partnerName.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("postagePartner.partnerName", "%" + partnerName.trim() + "%"));
        }
        if (type != null && !type.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("status.type", "%" + type.trim() + "%"));
        }

        List<Orders> ordersList = criteria.list();
        return ordersList.stream()
                        .map(ordersTransformer::transform)
                        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrdersDto> getOrdersByUserId(Integer userId) {
        log.info("OrdersDaoImpl.getOrdersByUserId() invoked with userId: {}", userId);
        Criteria criteria = getCurrentSession().createCriteria(Orders.class, "orders");
        
        // Join with user entity
        criteria.createAlias("user", "user", JoinType.INNER_JOIN);
        
        // Add user filter
        criteria.add(Restrictions.eq("user.id", userId));
        
        // Add active orders filter (optional - you can remove this if you want to show all orders)
        criteria.add(Restrictions.eq("isActive", true));
        
        // Order by creation date (newest first)
        criteria.addOrder(org.hibernate.criterion.Order.desc("createdAt"));
        
        List<Orders> ordersList = criteria.list();
        return ordersList.stream()
                        .map(ordersTransformer::transform)
                        .collect(Collectors.toList());
    }
}