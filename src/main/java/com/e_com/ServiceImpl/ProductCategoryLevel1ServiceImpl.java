package com.e_com.ServiceImpl;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel1Dto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryLevel1Service;
import com.e_com.Service.BL.ProductCategoryLevel1ServiceBL;
import com.e_com.Service.Utils.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * Title: ProductCategoryServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 17, 2025
 * @time 12:12:04 AM
 * @version 1.0
 **/

@Component
@Slf4j
public class ProductCategoryLevel1ServiceImpl implements ProductCategoryLevel1Service {

    @Autowired
    private ProductCategoryLevel1ServiceBL productCategoryLevel1ServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.info("ProductCategoryLevel1ServiceImpl.saveProductCategoryLevel1 invoked");
        ResponseDto responseDto = null;
        try {
            if (productCategoryLevel1Dto == null || productCategoryLevel1Dto.getName() == null || 
                productCategoryLevel1Dto.getName().trim().isEmpty()) {
                log.info("Invalid ProductCategoryLevel1 data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY_LEVEL1_DETAILS);
            }
            ProductCategoryLevel1Dto savedProductCategoryLevel1Dto = productCategoryLevel1ServiceBL.saveProductCategoryLevel1(productCategoryLevel1Dto);
            if (savedProductCategoryLevel1Dto != null) {
                log.info("ProductCategoryLevel1 Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedProductCategoryLevel1Dto);
            } else {
                log.info("Unable to save ProductCategoryLevel1 details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY_LEVEL1_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving ProductCategoryLevel1 details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_CATEGORY_LEVEL1_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto) {
        log.info("ProductCategoryLevel1ServiceImpl.updateProductCategoryLevel1 invoked");
        ResponseDto responseDto = null;
        try {
            if (productCategoryLevel1Dto == null || productCategoryLevel1Dto.getId() == null || 
                productCategoryLevel1Dto.getName() == null || productCategoryLevel1Dto.getName().trim().isEmpty()) {
                log.info("Invalid ProductCategoryLevel1 data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL1_DETAILS);
            }
            ProductCategoryLevel1Dto updatedProductCategoryLevel1Dto = productCategoryLevel1ServiceBL.updateProductCategoryLevel1(productCategoryLevel1Dto);
            if (updatedProductCategoryLevel1Dto != null) {
                log.info("ProductCategoryLevel1 Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedProductCategoryLevel1Dto);
            } else {
                log.info("Unable to update ProductCategoryLevel1 details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL1_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ProductCategoryLevel1 details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATEGORY_LEVEL1_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductCategoryLevel1Status(Integer productCategoryLevel1Id, Boolean status) {
        log.info("ProductCategoryLevel1ServiceImpl.updateProductCategoryLevel1Status invoked with productCategoryLevel1Id: {}, status: {}", 
                 productCategoryLevel1Id, status);
        ResponseDto responseDto = null;
        try {
            if (productCategoryLevel1Id == null) {
                log.info("Invalid productCategoryLevel1Id provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL1_STATUS);
            }
            ProductCategoryLevel1Dto updatedProductCategoryLevel1Dto = productCategoryLevel1ServiceBL.updateProductCategoryLevel1Status(productCategoryLevel1Id, status);
            if (updatedProductCategoryLevel1Dto != null) {
                log.info("ProductCategoryLevel1 Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedProductCategoryLevel1Dto);
            } else {
                log.info("Unable to update ProductCategoryLevel1 status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL1_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ProductCategoryLevel1 status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATEGORY_LEVEL1_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageProductCategoryLevel1(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductCategoryLevel1ServiceImpl.getAllPageProductCategoryLevel1() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_CATEGORY_LEVEL1_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = productCategoryLevel1ServiceBL.getAllPageProductCategoryLevel1(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated ProductCategoryLevel1 details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated ProductCategoryLevel1 details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_CATEGORY_LEVEL1_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated ProductCategoryLevel1 details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_CATEGORY_LEVEL1_DETAILS);
        }
        return responseDto;
    } 

    @Override
    public ResponseDto getAllBySearchProductCategoryLevel1(String name) {
        log.info("ProductCategoryLevel1ServiceImpl.getAllBySearchProductCategoryLevel1() invoked with name: {}", name);
        ResponseDto responseDto = null;
        try {
            List<ProductCategoryLevel1Dto> productCategoryLevel1List = productCategoryLevel1ServiceBL.getAllBySearchProductCategoryLevel1(name);
            log.info("Retrieved ProductCategoryLevel1 details by search.");
            responseDto = serviceUtil.getServiceResponse(productCategoryLevel1List);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving ProductCategoryLevel1 details by search.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_CATEGORY_LEVEL1_DETAILS);
        }
        return responseDto;
    }
}