package com.e_com.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.PaymentDao;
import com.e_com.Domain.Payment;
import com.e_com.Dto.PaymentDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
import com.e_com.Transformer.PaymentTransformer;
import org.hibernate.sql.JoinType;

import lombok.extern.slf4j.Slf4j;
/**
 * Title: PaymentDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 19, 2025
 * @time 9:24:17 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class PaymentDaoImpl extends BaseDaoImpl<Payment> implements PaymentDao {

    @Autowired
    PaymentTransformer paymentTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public PaymentDto savePayment(PaymentDto paymentDto) {
        log.info("PaymentDaoImpl.savePayment() invoked.");
        try {
            Payment payment = paymentTransformer.reverseTransform(paymentDto);
            Payment savedPayment = saveOrUpdate(payment);
            return paymentTransformer.transform(savedPayment);
        } catch (Exception e) {
            log.error("Error saving Payment: {}", e.getMessage());
            throw e;
        }
    }
    
    @Transactional
    public PaymentDto updatePayment(PaymentDto paymentDto) {
        log.info("PaymentDaoImpl.updatePayment() invoked.");
        try {
            Payment payment = paymentTransformer.reverseTransform(paymentDto);
            Payment updatedPayment = saveOrUpdate(payment);
            return paymentTransformer.transform(updatedPayment);
        } catch (Exception e) {
            log.error("Error updating Payment: {}", e.getMessage());
            throw e;
        }
    }
    
    @Transactional
    public PaymentDto checkPaymentAvailability(Integer paymentId) {
        log.info("PaymentDaoImpl.checkPaymentAvailability() invoked with paymentId: {}", paymentId);
        Criteria criteria = getCurrentSession().createCriteria(Payment.class, "payment");
        criteria.add(Restrictions.eq("id", paymentId));
        Payment payment = (Payment) criteria.uniqueResult();
        return payment != null ? paymentTransformer.transform(payment) : null;
    }
    
    @Override
    @Transactional
    public PaginatedResponseDto getAllPagePayment(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("PaymentDaoImpl.getAllPagePayment() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);

        PaginatedResponseDto paginatedResponseDto = null;
        List<Payment> paymentList = null;

        String countQuery = "SELECT COUNT(*) FROM payment";

        int count = jdbcTemplate.queryForObject(countQuery, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(Payment.class, "payment");

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        paymentList = criteria.list();

        if (paymentList != null && !paymentList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(paymentList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(paymentList.stream()
                    .map(paymentTransformer::transform)
                    .collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
    @Override
    @Transactional
    public List<PaymentDto> getAllBySearchPayment(String title, String firstName, String partnerName, String type) {
        log.info("PaymentDaoImpl.getAllBySearchPayment() invoked with title: {}, firstName: {}, partnerName: {}, type: {}", 
                 title, firstName, partnerName, type);
        
        Criteria criteria = getCurrentSession().createCriteria(Payment.class, "payment");

        criteria.createAlias("order", "order", JoinType.INNER_JOIN);               
        criteria.createAlias("order.user", "user", JoinType.INNER_JOIN);          
        criteria.createAlias("order.postagePartner", "postagePartner", JoinType.INNER_JOIN); 
        criteria.createAlias("order.status", "status", JoinType.INNER_JOIN);   

        if (title != null && !title.trim().isEmpty()) {
          
            criteria.add(Restrictions.ilike("order.title", "%" + title.trim() + "%"));
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

        List<Payment> paymentList = criteria.list();

        return paymentList.stream()
                          .map(paymentTransformer::transform)
                          .collect(Collectors.toList());
    }



}
