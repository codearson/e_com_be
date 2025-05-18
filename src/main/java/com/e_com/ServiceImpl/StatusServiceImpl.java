package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;
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
    
    @Override
    public ResponseDto updateStatus(StatusDto statusDto) {
        log.info("StatusServiceImpl.updateStatus invoked");
        ResponseDto responseDto = null;
        try {
        	StatusDto updatedStatusDto = statusServiceBL.updateStatus(statusDto);
            if (updatedStatusDto != null) {
                log.info("Status Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedStatusDto);
            } else {
                log.info("Unable to update Status details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_STATUS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Status details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_STATUS_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateForStatus(Integer statusId, Boolean status) {
        log.info("StatusServiceImpl.updateForStatus invoked with statusId: {}, status: {}", statusId, status);
        ResponseDto responseDto = null;
        try {
            StatusDto updatedStatusDto = statusServiceBL.updateForStatus(statusId, status);
            if (updatedStatusDto != null) {
                log.info("Status updated successfully.");
                responseDto = serviceUtil.getServiceResponse(updatedStatusDto);
            } else {
                log.info("Unable to update Status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_FOR_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_FOR_STATUS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllPageStatus(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("StatusServiceImpl.getAllPageStatus() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        
        ResponseDto responseDto = null;
        
        try {
            PaginatedResponseDto paginatedResponseDto = statusServiceBL.getAllPageStatus(pageNumber, pageSize, status, searchParameters);
            
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Status details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Status details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_STATUS_DETAILS);
            }
            
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Status details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_STATUS_DETAILS);
        }
        
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllStatus(String statusName) {
        log.info("StatusServiceImpl.getAllStatus() invoked with statusName: {}", statusName);
        ResponseDto responseDto = null;
        try {
            List<StatusDto> statusList = statusServiceBL.getAllStatus(statusName);
            if (statusList != null && !statusList.isEmpty()) {
                log.info("Retrieved all Status details.");
                responseDto = serviceUtil.getServiceResponse(statusList);
            } else {
                log.info("Unable to retrieve all Status details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_STATUS_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all Status details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_STATUS_DETAILS);
        }
        return responseDto;
    }




}
