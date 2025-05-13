package com.e_com.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BrandService;
import com.e_com.Service.BL.BrandServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BrandServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 13 May 2025
 * @time 19:06:13
 * @version 1.0
 **/

@Component
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandServiceBL brandServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveBrand(BrandDto brandDto) {
        log.info("BrandServiceImpl.saveBrand invoked");
        ResponseDto responseDto = null;
        try {
            BrandDto savedBrandDto = brandServiceBL.saveBrand(brandDto);
            if (savedBrandDto != null) {
                log.info("Brand Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedBrandDto);
            } else {
                log.info("Unable to save Brand details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_BRAND_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving Brand details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_BRAND_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateBrand(BrandDto brandDto) {
        log.info("BrandServiceImpl.updateBrand invoked");
        ResponseDto responseDto = null;
        try {
            BrandDto updatedBrandDto = brandServiceBL.updateBrand(brandDto);
            if (updatedBrandDto != null) {
                log.info("Brand Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedBrandDto);
            } else {
                log.info("Unable to update Brand details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_BRAND_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Brand details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_BRAND_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateBrandStatus(Integer brandId, Boolean status) {
        log.info("BrandServiceImpl.updateBrandStatus invoked with brandId: {}, status: {}", brandId, status);
        ResponseDto responseDto = null;
        try {
            BrandDto updatedBrandDto = brandServiceBL.updateBrandStatus(brandId, status);
            if (updatedBrandDto != null) {
                log.info("Brand Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedBrandDto);
            } else {
                log.info("Unable to update Brand status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_BRAND_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Brand status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_BRAND_STATUS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllPageBrand(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("BrandServiceImpl.getAllPageBrand() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            PaginatedResponseDto paginatedResponseDto = brandServiceBL.getAllPageBrand(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated Brand details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated Brand details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BRAND_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated Brand details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BRAND_DETAILS);
        }
        return responseDto;
    }
}
