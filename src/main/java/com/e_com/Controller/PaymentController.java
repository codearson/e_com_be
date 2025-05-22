package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.e_com.Config.SecurityConfig;
import com.e_com.Dto.PaymentDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BL.PaymentServiceBL;
import com.e_com.Service.PaymentService;
import com.e_com.Service.Utils.HttpReqRespUtils;

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
    
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updatePayment(@RequestBody PaymentDto paymentDto) {
        log.info("PaymentController.updatePayment() invoked");
        return paymentService.updatePayment(paymentDto);
    }
    
    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updatePaymentStatus(@RequestParam("paymentId") Integer paymentId,@RequestParam("status") Boolean status) {
        log.info("PaymentController.updatePaymentStatus() invoked with paymentId: {}, status: {}", paymentId, status);
        return paymentService.updatePaymentStatus(paymentId, status);
    }
    
    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPagePayment(@RequestParam("pageNumber") int pageNumber,
                                          @RequestParam("pageSize") int pageSize,
                                          @RequestParam("status") Boolean status,
                                          WebRequest webRequest) {
        log.info("PaymentController.getAllPagePayment() invoked with pageNumber: {}, pageSize: {}, status: {}",
                 pageNumber, pageSize, status);
        return paymentService.getAllPagePayment(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
    }
    
    @GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllBySearchPayment(@RequestParam(value = "title", required = false) String title,
                                             @RequestParam(value = "firstName", required = false) String firstName,
                                             @RequestParam(value = "partnerName", required = false) String partnerName,
                                             @RequestParam(value = "type", required = false) String type) {
        log.info("PaymentController.getAllBySearchPayment() invoked with title: {}, firstName: {}, partnerName: {}, type: {}",
                 title, firstName, partnerName, type);
        return paymentService.getAllBySearchPayment(title, firstName, partnerName, type);
    }

}
