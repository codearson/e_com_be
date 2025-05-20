package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.BankDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BankService;
import com.e_com.Service.BL.BankServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;



/**
 * Title: BankServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 15, 2025
 * @time 7:56:41 PM
 * @version 1.0
 **/



@Component
@Slf4j
public class BankServiceImpl implements BankService {

    @Autowired
    private BankServiceBL bankServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveBank(BankDto bankDto) {
        log.info("BankServiceImpl.saveBank invoked");
        ResponseDto responseDto = null;
        try {
            BankDto savedBankDto = bankServiceBL.saveBank(bankDto);
            if (savedBankDto != null) {
                log.info("Bank details saved.");
                responseDto = serviceUtil.getServiceResponse(savedBankDto);
            } else {
                log.info("Unable to save Bank details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_BANK_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurred while saving Bank details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_BANK_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateBank(BankDto bankDto) {
        log.info("BankServiceImpl.updateBank invoked");
        ResponseDto responseDto = null;
        try {
            BankDto updatedBankDto = bankServiceBL.updateBank(bankDto);
            if (updatedBankDto != null) {
                log.info("Bank Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedBankDto);
            } else {
                log.info("Unable to update Bank details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_BANK_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Bank details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_BANK_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateBankStatus(Integer bankId, Boolean status) {
        log.info("BankServiceImpl.updateBankStatus invoked with bankId: {}, status: {}", bankId, status);
        ResponseDto responseDto = null;
        try {
            BankDto updatedBankDto = bankServiceBL.updateBankStatus(bankId, status);
            if (updatedBankDto != null) {
                log.info("Bank Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedBankDto);
            } else {
                log.info("Unable to update Bank status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_BANK_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Bank status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_BANK_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageBank(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("BankServiceImpl.getAllPageBank() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            PaginatedResponseDto paginatedResponseDto = bankServiceBL.getAllPageBank(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Bank details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Bank details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BANK_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Bank details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BANK_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllBank(String bankName) {
        log.info("BankServiceImpl.getAllBank() invoked with bankName: {}", bankName);
        ResponseDto responseDto = null;
        try {
            List<BankDto> bankList = bankServiceBL.getAllBank(bankName);
            if (bankList != null && !bankList.isEmpty()) {
                log.info("Retrieved all Bank details.");
                responseDto = serviceUtil.getServiceResponse(bankList);
            } else {
                log.info("Unable to retrieve all Bank details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BANK_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all Bank details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BANK_DETAILS);
        }
        return responseDto;
    }
}
