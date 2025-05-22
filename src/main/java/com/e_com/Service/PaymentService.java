package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.PaymentDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: PaymentService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 19, 2025
 * @time 7:35:35 PM
 * @version 1.0
 **/

public interface PaymentService {

    ResponseDto savePayment(PaymentDto paymentDto);
    
    ResponseDto updatePayment(PaymentDto paymentDto);
    
    ResponseDto updatePaymentStatus(Integer paymentId, Boolean status);
    
    ResponseDto getAllPagePayment(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);

    ResponseDto getAllBySearchPayment(String title, String firstName, String partnerName, String type);

}
