package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.e_com.Dto.ResponseDto;
import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductImageDto;
import com.e_com.Service.ProductImageService;
import com.e_com.Service.BL.ProductImageServiceBL;
import com.e_com.Service.Utils.ServiceUtil;


import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductImageServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 28, 2025
 * @time 10:47:53 PM
 * @version 1.0
 **/




@Component
@Slf4j
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageServiceBL productImageServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveProductImage(ProductImageDto productImageDto) {
        log.info("ProductImageServiceImpl.saveProductImage invoked");
        ResponseDto responseDto = null;
        try {
            ProductImageDto savedDto = productImageServiceBL.saveProductImage(productImageDto);
            if (savedDto != null) {
                log.info("ProductImage saved.");
                responseDto = serviceUtil.getServiceResponse(savedDto);
            } else {
                log.info("Unable to save ProductImage.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_IMAGE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving ProductImage.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_IMAGE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductImage(ProductImageDto productImageDto) {
        log.info("ProductImageServiceImpl.updateProductImage invoked");
        ResponseDto responseDto = null;
        try {
            ProductImageDto updatedDto = productImageServiceBL.updateProductImage(productImageDto);
            if (updatedDto != null) {
                log.info("ProductImage updated.");
                responseDto = serviceUtil.getServiceResponse(updatedDto);
            } else {
                log.info("Unable to update ProductImage.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_IMAGE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ProductImage.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_IMAGE_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto checkProductImageAvailability(Integer productImageId) {
        log.info("ProductImageServiceImpl.checkProductImageAvailability invoked with productImageId: {}", productImageId);
        ResponseDto responseDto = null;
        try {
            ProductImageDto availableDto = productImageServiceBL.checkProductImageAvailability(productImageId);
            if (availableDto != null) {
                log.info("ProductImage is available.");
                responseDto = serviceUtil.getServiceResponse(availableDto);
            } else {
                log.info("ProductImage not found.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_PRODUCT_IMAGE_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while checking ProductImage availability.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_PRODUCT_IMAGE_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageProductImage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductImageServiceImpl.getAllPageProductImage invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            PaginatedResponseDto paginatedResponseDto = productImageServiceBL.getAll(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated ProductImage list.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve ProductImage list.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_IMAGE_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated ProductImage list.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_IMAGE_DETAILS);
        }
        try {
        	   // DAO call
        	} catch (Exception e) {
        	   log.info("Unable to retrieve ProductImage list.");
        	}

        return responseDto;
    }
    
    public ResponseDto getAllProductImage(String productImageUrl) {
        log.info("BrandServiceImpl.getAllBrand() invoked with brandName: {}", productImageUrl);
        ResponseDto responseDto = null;
        try {
            List<ProductImageDto> productImageList = ProductImageServiceBL.getAllProductImage(productImageUrl);
            if (productImageList != null && !productImageList.isEmpty()) {
                log.info("Retrieved all ProductImage details.");
                responseDto = serviceUtil.getServiceResponse(productImageList);
            } else {
                log.info("Unable to retrieve all Brand details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BRAND_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all Brand details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BRAND_DETAILS);
        }
        return responseDto;
    }


}