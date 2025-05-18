package com.e_com.ServiceImpl;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryService;
import com.e_com.Service.BL.ProductCategoryServiceBL;
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
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryServiceBL productCategoryServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryServiceImpl.saveProductCategory invoked");
        ResponseDto responseDto = null;
        try {
            if (productCategoryDto == null || productCategoryDto.getName() == null || 
                productCategoryDto.getName().trim().isEmpty()) {
                log.info("Invalid ProductCategory data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY_DETAILS);
            }
            ProductCategoryDto savedProductCategoryDto = productCategoryServiceBL.saveProductCategory(productCategoryDto);
            if (savedProductCategoryDto != null) {
                log.info("ProductCategory Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedProductCategoryDto);
            } else {
                log.info("Unable to save ProductCategory details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving ProductCategory details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_CATEGORY_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductCategory(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryServiceImpl.updateProductCategory invoked");
        ResponseDto responseDto = null;
        try {
            if (productCategoryDto == null || productCategoryDto.getId() == null || 
                productCategoryDto.getName() == null || productCategoryDto.getName().trim().isEmpty()) {
                log.info("Invalid ProductCategory data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_DETAILS);
            }
            ProductCategoryDto updatedProductCategoryDto = productCategoryServiceBL.updateProductCategory(productCategoryDto);
            if (updatedProductCategoryDto != null) {
                log.info("ProductCategory Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedProductCategoryDto);
            } else {
                log.info("Unable to update ProductCategory details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ProductCategory details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATEGORY_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductCategoryStatus(Integer productCategoryId, Boolean status) {
        log.info("ProductCategoryServiceImpl.updateProductCategoryStatus invoked with productCategoryId: {}, status: {}", 
                 productCategoryId, status);
        ResponseDto responseDto = null;
        try {
            if (productCategoryId == null) {
                log.info("Invalid productCategoryId provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_STATUS);
            }
            ProductCategoryDto updatedProductCategoryDto = productCategoryServiceBL.updateProductCategoryStatus(productCategoryId, status);
            if (updatedProductCategoryDto != null) {
                log.info("ProductCategory Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedProductCategoryDto);
            } else {
                log.info("Unable to update ProductCategory status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ProductCategory status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATEGORY_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductCategoryServiceImpl.getAllPageProductCategory() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_CATEGORY_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = productCategoryServiceBL.getAllPageProductCategory(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated ProductCategory details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated ProductCategory details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_CATEGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated ProductCategory details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_CATEGORY_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllBySearchProductCategory(String name) {
        log.info("ProductCategoryServiceImpl.getAllBySearchProductCategory() invoked with name: {}", name);
        ResponseDto responseDto = null;
        try {
            List<ProductCategoryDto> productCategoryList = productCategoryServiceBL.getAllBySearchProductCategory(name);
            log.info("Retrieved ProductCategory details by search.");
            responseDto = serviceUtil.getServiceResponse(productCategoryList);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving ProductCategory details by search.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_CATEGORY_DETAILS);
        }
        return responseDto;
    }
}