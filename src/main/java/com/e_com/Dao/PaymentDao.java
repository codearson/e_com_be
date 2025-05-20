package com.e_com.Dao;

import com.e_com.Domain.Payment;
import com.e_com.Dto.PaymentDto;

/**
 * Title: PaymentDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 19, 2025
 * @time 9:08:49 PM
 * @version 1.0
 **/

public interface PaymentDao extends BaseDao<Payment> {
    
    PaymentDto savePayment(PaymentDto paymentDto);
    
}
