package com.e_com.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaymentDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.PaymentService;
import com.e_com.Service.BL.PaymentServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PaymentServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 19, 2025
 * @time 8:34:35 PM
 * @version 1.0
 **/

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentServiceBL paymentServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto savePayment(PaymentDto paymentDto) {
        log.info("PaymentServiceImpl.savePayment invoked");
        ResponseDto responseDto = null;
        try {
            if (paymentDto == null || paymentDto.getOrdersDto() == null || paymentDto.getAmount() == null) {
                log.info("Invalid Payment data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PAYMENT_DETAILS);
            }

            PaymentDto savedPaymentDto = paymentServiceBL.savePayment(paymentDto);
            if (savedPaymentDto != null) {
                log.info("Payment Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedPaymentDto);
            } else {
                log.info("Unable to save Payment details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PAYMENT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Payment details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PAYMENT_DETAILS);
        }
        return responseDto;
    }
}
