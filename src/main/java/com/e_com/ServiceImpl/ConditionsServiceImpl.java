package com.e_com.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ConditionsService;
import com.e_com.Service.BL.ConditionsServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * Title: ConditionsServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 14 May 2025
 * @time 6:05:35 pm
 * @version 1.0
 **/

@Component
@Slf4j
public class ConditionsServiceImpl implements ConditionsService {

    @Autowired
    private ConditionsServiceBL conditionsServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveConditions(ConditionsDto conditionsDto) {
        log.info("ConditionsServiceImpl.saveConditions invoked");
        ResponseDto responseDto = null;

        try {
            ConditionsDto savedConditionsDto = conditionsServiceBL.saveConditions(conditionsDto);
            if (savedConditionsDto != null) {
                log.info("Conditions details saved.");
                responseDto = serviceUtil.getServiceResponse(savedConditionsDto);
            } else {
                log.warn("Unable to save conditions details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_CONDITIONS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving conditions details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_CONDITIONS_DETAILS);
        }

        return responseDto;
    }
    
    @Override
    public ResponseDto updateConditions(ConditionsDto conditionsDto) {
        log.info("ConditionsServiceImpl.updateConditions invoked");
        ResponseDto responseDto = null;
        try {
            ConditionsDto updatedConditionsDto = conditionsServiceBL.updateConditions(conditionsDto);
            if (updatedConditionsDto != null) {
                log.info("Conditions details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedConditionsDto);
            } else {
                log.info("Unable to update conditions details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_CONDITIONS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating conditions details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_CONDITIONS_DETAILS);
        }
        return responseDto;
    }

}
