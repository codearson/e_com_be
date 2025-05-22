package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaymentDto;
import com.e_com.Dto.PaginatedResponseDto;
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
    
    @Override
    public ResponseDto updatePayment(PaymentDto paymentDto) {
        log.info("PaymentServiceImpl.updatePayment invoked");
        ResponseDto responseDto = null;
        try {
            if (paymentDto == null || paymentDto.getId() == null || paymentDto.getOrdersDto() == null || paymentDto.getAmount() == null) {
                log.info("Invalid Payment data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PAYMENT_DETAILS);
            }

            PaymentDto updatedPaymentDto = paymentServiceBL.updatePayment(paymentDto);
            if (updatedPaymentDto != null) {
                log.info("Payment Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedPaymentDto);
            } else {
                log.info("Unable to update Payment details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PAYMENT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Payment details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PAYMENT_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updatePaymentStatus(Integer paymentId, Boolean status) {
        log.info("PaymentServiceImpl.updatePaymentStatus invoked with paymentId: {}, status: {}", paymentId, status);
        ResponseDto responseDto = null;
        try {
            if (paymentId == null) {
                log.info("Invalid paymentId provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PAYMENT_STATUS);
            }
            PaymentDto updatedPaymentDto = paymentServiceBL.updatePaymentStatus(paymentId, status);
            if (updatedPaymentDto != null) {
                log.info("Payment Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedPaymentDto);
            } else {
                log.info("Unable to update Payment status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PAYMENT_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Payment status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PAYMENT_STATUS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllPagePayment(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("PaymentServiceImpl.getAllPagePayment() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PAYMENT_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = paymentServiceBL.getAllPagePayment(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Payment details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Payment details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PAYMENT_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Payment details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PAYMENT_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllBySearchPayment(String title, String firstName, String partnerName, String type) {
        log.info("PaymentServiceImpl.getAllBySearchPayment() invoked with title: {}, firstName: {}, partnerName: {}, type: {}", 
                 title, firstName, partnerName, type);
        ResponseDto responseDto = null;
        try {
            List<PaymentDto> paymentList = paymentServiceBL.getAllBySearchPayment(title, firstName, partnerName, type);
            log.info("Retrieved Payment details by search.");
            responseDto = serviceUtil.getServiceResponse(paymentList);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Payment details by search.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PAYMENT_DETAILS);
        }
        return responseDto;
    }


}
