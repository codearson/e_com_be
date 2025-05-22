package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.e_com.Controller.OrdersController;
import com.e_com.Dao.PaymentDao;
import com.e_com.Dto.PaymentDto;
import com.e_com.Dto.PaginatedResponseDto;

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
    
    public PaymentDto updatePayment(PaymentDto paymentDto) {
        log.info("PaymentServiceBL.updatePayment() invoked.");
        if (paymentDto == null || paymentDto.getId() == null) {
            log.warn("PaymentDto or its ID is null");
            return null;
        }
        PaymentDto existingDto = paymentDao.checkPaymentAvailability(paymentDto.getId());
        if (existingDto == null) {
            log.warn("Payment with id {} not found.", paymentDto.getId());
            return null;
        }
        if (paymentDto.getOrdersDto() == null) {
            paymentDto.setOrdersDto(existingDto.getOrdersDto());
        }
        return paymentDao.updatePayment(paymentDto);
    }
    
    public PaymentDto updatePaymentStatus(Integer paymentId, Boolean status) {
        log.info("PaymentServiceBL.updatePaymentStatus() invoked with paymentId: {}, status: {}", paymentId, status);
        PaymentDto paymentDto = paymentDao.checkPaymentAvailability(paymentId);
        if (paymentDto != null) {
            paymentDto.setIsPaid(status);
            return paymentDao.updatePayment(paymentDto);
        } else {
            log.info("Payment with id {} not found.", paymentId);
            return null;
        }
    }
    
    public PaginatedResponseDto getAllPagePayment(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("PaymentServiceBL.getAllPagePayment() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return paymentDao.getAllPagePayment(pageNumber, pageSize, status, searchParameters);
    }
    
    public List<PaymentDto> getAllBySearchPayment(String title, String firstName, String partnerName, String type) {
        log.info("PaymentServiceBL.getAllBySearchPayment() invoked with title: {}, firstName: {}, partnerName: {}, type: {}", 
                 title, firstName, partnerName, type);
        return paymentDao.getAllBySearchPayment(title, firstName, partnerName, type);
    }


}
