package com.e_com.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.PaymentDao;
import com.e_com.Domain.Payment;
import com.e_com.Dto.PaymentDto;
import com.e_com.Transformer.PaymentTransformer;

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
}
