package com.e_com.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.PaymentDao;
import com.e_com.Dto.PaymentDto;

import lombok.extern.slf4j.Slf4j;
/**
 * Title: PaymentServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 19, 2025
 * @time 8:50:06 PM
 * @version 1.0
 **/

@Slf4j
@Service
public class PaymentServiceBL {

    @Autowired
    private PaymentDao paymentDao;

    public PaymentDto savePayment(PaymentDto paymentDto) {
        log.info("PaymentServiceBL.savePayment() invoked.");
        if (paymentDto != null) {
            return paymentDao.savePayment(paymentDto);
        }
        return null;
    }
}
