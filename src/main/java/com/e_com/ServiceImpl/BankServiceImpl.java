package com.e_com.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.BankDto;
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
}
