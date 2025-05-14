package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.PostagePartnerDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.PostagePartnerService;
import com.e_com.Service.BL.PostagePartnerServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: PostagePartnerServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 14, 2025
 * @version 1.0
 **/

@Component
@Slf4j
public class PostagePartnerServiceImpl implements PostagePartnerService {

	@Autowired
    private PostagePartnerServiceBL postagePartnerServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;
    
    @Override
    public ResponseDto save(PostagePartnerDto postagePartnerDto) {
        log.info("PostagePartnerServiceImpl.save invoked");
        ResponseDto responseDto = null;
        try {
        	PostagePartnerDto savedPostagePartnerDto = postagePartnerServiceBL.save(postagePartnerDto);
            if (savedPostagePartnerDto != null) {
                log.info("Postage Partner Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedPostagePartnerDto);
            } else {
                log.info("Unable to save Postage Partner details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_POSTAGE_PARTNER_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Postage Partner details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_POSTAGE_PARTNER_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto update(PostagePartnerDto postagePartnerDto) {
        log.info("PostagePartnerServiceImpl.update invoked");
        ResponseDto responseDto = null;
        try {
        	PostagePartnerDto updatedPostagePartnerDto = postagePartnerServiceBL.update(postagePartnerDto);
            if (updatedPostagePartnerDto != null) {
                log.info("Postage Partner Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedPostagePartnerDto);
            } else {
                log.info("Unable to update Postage Partner details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_POSTAGE_PARTNER_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Postage Partner details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_POSTAGE_PARTNER_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateStatus(Integer postagePartnerId, Boolean status) {
        log.info("PostagePartnerServiceImpl.updateStatus invoked with postagePartnerId: {}, status: {}", postagePartnerId, status);
        ResponseDto responseDto = null;
        try {
        	PostagePartnerDto updatedPostagePartnerDto = postagePartnerServiceBL.updateStatus(postagePartnerId, status);
            if (updatedPostagePartnerDto != null) {
                log.info("Postage Partner Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedPostagePartnerDto);
            } else {
                log.info("Unable to update Postage Partner status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_POSTAGE_PARTNER_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Postage Partner status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_POSTAGE_PARTNER_STATUS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("PostagePartnerServiceImpl.getAllPage() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            PaginatedResponseDto paginatedResponseDto = postagePartnerServiceBL.getAllPage(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Postage Partner details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Postage Partner details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_POSTAGE_PARTNER_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Postage Partner details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_POSTAGE_PARTNER_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAll(String partnerName) {
        log.info("PostagePartnerServiceImpl.getAll() invoked with partnerName: {}", partnerName);
        ResponseDto responseDto = null;
        try {
            List<PostagePartnerDto> postagePartnerList = postagePartnerServiceBL.getAll(partnerName);
            if (postagePartnerList != null && !postagePartnerList.isEmpty()) {
                log.info("Retrieved all postage partner details.");
                responseDto = serviceUtil.getServiceResponse(postagePartnerList);
            } else {
                log.info("Unable to retrieve all postage partner details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_POSTAGE_PARTNER_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all postage partner details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_POSTAGE_PARTNER_DETAILS);
        }
        return responseDto;
    }
	
}
