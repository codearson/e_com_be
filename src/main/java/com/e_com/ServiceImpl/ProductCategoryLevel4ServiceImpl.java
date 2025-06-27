package com.e_com.ServiceImpl;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel4Dto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryLevel4Service;
import com.e_com.Service.BL.ProductCategoryLevel4ServiceBL;
import com.e_com.Service.Utils.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * Title: ProductCategoryLevel4ServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date Jan 27, 2025
 * @time 5:30 PM
 * @version 1.0
 **/

@Component
@Slf4j
public class ProductCategoryLevel4ServiceImpl implements ProductCategoryLevel4Service {

    @Autowired
    private ProductCategoryLevel4ServiceBL productCategoryLevel4ServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        log.info("ProductCategoryLevel4ServiceImpl.saveProductCategoryLevel4 invoked");
        ResponseDto responseDto = null;
        try {
            if (productCategoryLevel4Dto == null || productCategoryLevel4Dto.getName() == null || 
                productCategoryLevel4Dto.getName().trim().isEmpty()) {
                log.info("Invalid ProductCategoryLevel4 data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY_LEVEL4_DETAILS);
            }
            
            // Validate that ProductCategoryLevel3 is provided
            if (productCategoryLevel4Dto.getProductCategoryLevel3Dto() == null || 
                productCategoryLevel4Dto.getProductCategoryLevel3Dto().getId() == null) {
                log.info("ProductCategoryLevel3 is required for ProductCategoryLevel4.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY_LEVEL4_DETAILS);
            }
            
            ProductCategoryLevel4Dto savedProductCategoryLevel4Dto = productCategoryLevel4ServiceBL.saveProductCategoryLevel4(productCategoryLevel4Dto);
            if (savedProductCategoryLevel4Dto != null) {
                log.info("ProductCategoryLevel4 Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedProductCategoryLevel4Dto);
            } else {
                log.info("Unable to save ProductCategoryLevel4 details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATEGORY_LEVEL4_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving ProductCategoryLevel4 details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_CATEGORY_LEVEL4_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto) {
        log.info("ProductCategoryLevel4ServiceImpl.updateProductCategoryLevel4 invoked");
        ResponseDto responseDto = null;
        try {
            if (productCategoryLevel4Dto == null || productCategoryLevel4Dto.getId() == null || 
                productCategoryLevel4Dto.getName() == null || productCategoryLevel4Dto.getName().trim().isEmpty()) {
                log.info("Invalid ProductCategoryLevel4 data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL4_DETAILS);
            }
            
            // Validate that ProductCategoryLevel3 is provided
            if (productCategoryLevel4Dto.getProductCategoryLevel3Dto() == null || 
                productCategoryLevel4Dto.getProductCategoryLevel3Dto().getId() == null) {
                log.info("ProductCategoryLevel3 is required for ProductCategoryLevel4 update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL4_DETAILS);
            }
            
            ProductCategoryLevel4Dto updatedProductCategoryLevel4Dto = productCategoryLevel4ServiceBL.updateProductCategoryLevel4(productCategoryLevel4Dto);
            if (updatedProductCategoryLevel4Dto != null) {
                log.info("ProductCategoryLevel4 Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedProductCategoryLevel4Dto);
            } else {
                log.info("Unable to update ProductCategoryLevel4 details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL4_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ProductCategoryLevel4 details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATEGORY_LEVEL4_DETAILS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateProductCategoryLevel4Status(Integer productCategoryLevel4Id, Boolean status) {
        log.info("ProductCategoryLevel4ServiceImpl.updateProductCategoryLevel4Status invoked with productCategoryLevel4Id: {}, status: {}", 
                 productCategoryLevel4Id, status);
        ResponseDto responseDto = null;
        try {
            if (productCategoryLevel4Id == null) {
                log.info("Invalid productCategoryLevel4Id provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL4_STATUS);
            }
            ProductCategoryLevel4Dto updatedProductCategoryLevel4Dto = productCategoryLevel4ServiceBL.updateProductCategoryLevel4Status(productCategoryLevel4Id, status);
            if (updatedProductCategoryLevel4Dto != null) {
                log.info("ProductCategoryLevel4 Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedProductCategoryLevel4Dto);
            } else {
                log.info("Unable to update ProductCategoryLevel4 status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATEGORY_LEVEL4_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating ProductCategoryLevel4 status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATEGORY_LEVEL4_STATUS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllPageProductCategoryLevel4(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ProductCategoryLevel4ServiceImpl.getAllPageProductCategoryLevel4() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            if (pageNumber < 1 || pageSize < 0) {
                log.info("Invalid pagination parameters provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_CATEGORY_LEVEL4_DETAILS);
            }
            PaginatedResponseDto paginatedResponseDto = productCategoryLevel4ServiceBL.getAllPageProductCategoryLevel4(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated ProductCategoryLevel4 details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated ProductCategoryLevel4 details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_CATEGORY_LEVEL4_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated ProductCategoryLevel4 details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_CATEGORY_LEVEL4_DETAILS);
        }
        return responseDto;
    } 

    @Override
    public ResponseDto getAllBySearchProductCategoryLevel4(String name) {
        log.info("ProductCategoryLevel4ServiceImpl.getAllBySearchProductCategoryLevel4() invoked with name: {}", name);
        ResponseDto responseDto = null;
        try {
            List<ProductCategoryLevel4Dto> productCategoryLevel4List = productCategoryLevel4ServiceBL.getAllBySearchProductCategoryLevel4(name);
            log.info("Retrieved ProductCategoryLevel4 details by search.");
            responseDto = serviceUtil.getServiceResponse(productCategoryLevel4List);
        } catch (Exception e) {
            log.error("Exception occurs while retrieving ProductCategoryLevel4 details by search.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_CATEGORY_LEVEL4_DETAILS);
        }
        return responseDto;
    }
} 