package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.Dto.PaymentDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.PaymentService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PaymentController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 19, 2025
 * @time 7:27:45 PM
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public ResponseDto savePayment(@RequestBody PaymentDto paymentDto) {
        log.info("PaymentController.savePayment() invoked");
        return paymentService.savePayment(paymentDto);
    }
}
