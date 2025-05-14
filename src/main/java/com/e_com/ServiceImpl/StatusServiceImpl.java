package com.e_com.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.StatusDto;
import com.e_com.Service.StatusService;
import com.e_com.Service.BL.StatusServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StatusServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 14, 2025
 * @time 6:26:29 PM
 * @version 1.0
 **/

@Component
@Slf4j
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusServiceBL statusServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveStatus(StatusDto statusDto) {
        log.info("StatusServiceImpl.saveStatus invoked");
        ResponseDto responseDto = null;
        try {
            StatusDto savedStatusDto = statusServiceBL.saveStatus(statusDto);
            if (savedStatusDto != null) {
                log.info("Status details saved successfully.");
                responseDto = serviceUtil.getServiceResponse(savedStatusDto);
            } else {
                log.info("Failed to save status details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_STATUS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurred while saving status details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_STATUS_DETAILS);
        }
        return responseDto;
    }
}
