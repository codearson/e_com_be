//package com.e_com.Transformer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.Payment;
import com.e_com.Dto.PaymentDto;
import com.e_com.Transformer.BaseTransformer;
import com.e_com.Transformer.OrdersTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PaymentTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 19, 2025
 * @time 6:03:29 PM
 * @version 1.0
 **/

@Slf4j
@Component
public class PaymentTransformer implements BaseTransformer<Payment, PaymentDto> {

    @Autowired
    private OrdersTransformer ordersTransformer;

    @Override
    public PaymentDto transform(Payment payment) {
        log.debug("Transforming Payment to PaymentDto for id: {}", payment != null ? payment.getId() : null);
        PaymentDto paymentDto = null;

        if (payment != null) {
            paymentDto = new PaymentDto();
            paymentDto.setId(payment.getId());
            paymentDto.setAmount(payment.getAmount());
            if (payment.getOrder() != null) {
                paymentDto.setOrdersDto(ordersTransformer.transform(payment.getOrder()));
            }
            paymentDto.setStatus(payment.getStatus());
        }

        return paymentDto;
    }

    @Override
    public Payment reverseTransform(PaymentDto paymentDto) {
        log.debug("Reverse transforming PaymentDto to Payment for id: {}", paymentDto != null ? paymentDto.getId() : null);
        Payment payment = null;

        if (paymentDto != null) {
            payment = new Payment();
            payment.setId(paymentDto.getId());
            payment.setAmount(paymentDto.getAmount());
            if (paymentDto.getOrdersDto() != null) {
                payment.setOrder(ordersTransformer.reverseTransform(paymentDto.getOrdersDto()));
            }
            payment.setStatus(paymentDto.getStatus());
        }

        return payment;
    }
}
