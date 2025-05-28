package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductSubCategoryDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductSubCategoryService;
import com.e_com.Service.BL.ProductSubCategoryServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductSubCategoryServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 5:07:45 PM
 * @version 1.0
 **/

@Slf4j
@Component
public class ProductSubCategoryServiceImpl implements ProductSubCategoryService{

	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	ProductSubCategoryServiceBL productSubCategoryServiceBL;
	
	@Override
	public ResponseDto getAllPageProductSubCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("ProductSubCategoryServiceImpl.getAllPageProductSubCategory() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		ResponseDto responseDto = null;
		try {
			if (pageNumber < 1 || pageSize < 0) {
				log.info("Invalid pagination parameters provided.");
				return serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAGINATED_PRODUCTSUBCATEGORY_DETAILS);
			}
			PaginatedResponseDto paginatedResponseDto = productSubCategoryServiceBL.getAllPageProductSubCategory(pageNumber, pageSize, status, searchParameters);
			if (paginatedResponseDto != null) {
				log.info("Retrieved paginated ProductSubCategory details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve paginated ProductSubCategory details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAGINATED_PRODUCTSUBCATEGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving paginated ProductSubCategory details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PAGINATED_PRODUCTSUBCATEGORY_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(ProductSubCategoryDto productSubCategoryDto) {
		log.info("ProductSubCategoryServiceImpl.save(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			ProductSubCategoryDto saveProductSubCategoryDto = productSubCategoryServiceBL.save(productSubCategoryDto);
			if (saveProductSubCategoryDto != null) {
				log.info("ProductSubCategory Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveProductSubCategoryDto);
			} else {
				log.info("Unable to save ProductSubCategory details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCTSUBCATEGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving ProductSubCategory details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCTSUBCATEGORY_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateProductSubCategory(ProductSubCategoryDto productSubCategoryDto) {
	    log.info("ProductSubCategoryServiceImpl.updateProductSubCategory() invoked");
	    ResponseDto responseDto = null;
	    try {
	    	ProductSubCategoryDto updatedProductSubCategory = productSubCategoryServiceBL.updateProductSubCategory(productSubCategoryDto);
	        if (updatedProductSubCategory != null) {
	            log.info("ProductSubCategory details updated successfully.");
	            responseDto = serviceUtil.getServiceResponse(updatedProductSubCategory);
	        } else {
	            log.info("Failed to update ProductSubCategory details.");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCTSUBCATEGORY_DETAILS);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while updating ProductSubCategory details.", e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCTSUBCATEGORY_DETAILS);
	    }
	    return responseDto;
	}

	
	@Override
	public ResponseDto getProductSubCategoryByName(String productSubCategoryName) {
	    log.info("ProductSubCategoryServiceImpl.getProductSubCategoryByName() invoked");

	    try {
	        List<ProductSubCategoryDto> productSubCategoryList = productSubCategoryServiceBL.getProductSubCategoryByName(productSubCategoryName);
	        if (!productSubCategoryList.isEmpty()) {
	            return serviceUtil.getServiceResponse(productSubCategoryList);
	        } else {
	            return serviceUtil.getErrorServiceResponse(
	                ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCTSUBCATEGORY_BY_NAME);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving productSubCategory by name", e);
	        return serviceUtil.getExceptionServiceResponseByProperties(
	            ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCTSUBCATEGORY_BY_NAME);
	    }
	}
	
	@Override
	public ResponseDto updateProductSubCategoryStatus(Integer productSubCategoryId, Boolean status) {
		log.info("InvoiceServiceImpl.updateProductSubCategoryStatus(ProductSubCategoryDto productSubCategoryDto) invoked");
		ResponseDto responseDto = null;
		try {
			ProductSubCategoryDto updatedProductSubCategoryStatusDto = productSubCategoryServiceBL.updateProductSubCategoryStatus(productSubCategoryId, status);
			if (updatedProductSubCategoryStatusDto != null) {
				log.info("ProductSubCategory Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedProductSubCategoryStatusDto);
			} else {
				log.info("Unable to update ProductSubCategory status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCTSUBCATEGORY_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating ProductSubCategory status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCTSUBCATEGORY_STATUS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getProductSubCategoryById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<ProductSubCategoryDto> productSubCategoryDtoList = productSubCategoryServiceBL.getProductSubCategoryById(id);
			if (productSubCategoryDtoList != null && !productSubCategoryDtoList.isEmpty()) {
				log.info("productSubCategory detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(productSubCategoryDtoList);
			} else {
				log.info("Unable to Retrive productSubCategory detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCTSUBCATEGORY_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the branch detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCTSUBCATEGORY_BY_ID);
		}
		return responseDto;
	}
	
	@Override
    public ResponseDto getAllProductSubCategory(String productSubCategoryName) {
        log.info("ProductSubCategoryServiceImpl.getAllProductSubCategory() invoked with productSubCategoryName: {}", productSubCategoryName);
        ResponseDto responseDto = null;
        try {
            List<ProductSubCategoryDto> productSubCategoryList = productSubCategoryServiceBL.getAllProductSubCategory(productSubCategoryName);
            if (productSubCategoryList != null && !productSubCategoryList.isEmpty()) {
                log.info("Retrieved all ProductSubCategory details.");
                responseDto = serviceUtil.getServiceResponse(productSubCategoryList);
            } else {
                log.info("Unable to retrieve all ProductSubCategory details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCTSUBCATEGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all ProductSubCategory details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCTSUBCATEGORY_DETAILS);
        }
        return responseDto;
    }
}
