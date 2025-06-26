package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel2Dto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryLevel2Service;
import com.e_com.Service.BL.ProductCategoryLevel2ServiceBL;
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
public class productCategoryLevel2ServiceImpl implements ProductCategoryLevel2Service{

	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	ProductCategoryLevel2ServiceBL productCategoryLevel2ServiceBL;
	
	@Override
	public ResponseDto getAllPageProductCategoryLevel2(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("ProductCategoryLevel2ServiceImpl.getAllPageProductCategoryLevel2() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		ResponseDto responseDto = null;
		try {
			if (pageNumber < 1 || pageSize < 0) {
				log.info("Invalid pagination parameters provided.");
				return serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAGINATED_PRODUCTCATEGORYLEVEL2_DETAILS);
			}
			PaginatedResponseDto paginatedResponseDto = productCategoryLevel2ServiceBL.getAllPageProductCategoryLevel2(pageNumber, pageSize, status, searchParameters);
			if (paginatedResponseDto != null) {
				log.info("Retrieved paginated ProductCategoryLevel2 details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve paginated ProductCategoryLevel2 details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAGINATED_PRODUCTCATEGORYLEVEL2_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving paginated ProductCategoryLevel2 details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PAGINATED_PRODUCTCATEGORYLEVEL2_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(ProductCategoryLevel2Dto productCategoryLevel2Dto) {
		log.info("productCategoryLevel2ServiceImpl.save(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			ProductCategoryLevel2Dto saveProductCategoryLevel2Dto = productCategoryLevel2ServiceBL.save(productCategoryLevel2Dto);
			if (saveProductCategoryLevel2Dto != null) {
				log.info("ProductCategoryLevel2 Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveProductCategoryLevel2Dto);
			} else {
				log.info("Unable to save ProductCategoryLevel2 details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCTCATEGORYLEVEL2_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving ProductCategoryLevel2 details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCTCATEGORYLEVEL2_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateProductCategoryLevel2(ProductCategoryLevel2Dto productCategoryLevel2Dto) {
	    log.info("ProductCategoryLevel2ServiceImpl.updateProductCategoryLevel2() invoked");
	    ResponseDto responseDto = null;
	    try {
	    	ProductCategoryLevel2Dto updatedProductCategoryLevel2 = productCategoryLevel2ServiceBL.updateProductCategoryLevel2(productCategoryLevel2Dto);
	        if (updatedProductCategoryLevel2 != null) {
	            log.info("ProductCategoryLevel2 details updated successfully.");
	            responseDto = serviceUtil.getServiceResponse(updatedProductCategoryLevel2);
	        } else {
	            log.info("Failed to update ProductCategoryLevel2 details.");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCTCATEGORYLEVEL2_DETAILS);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while updating ProductCategoryLevel2 details.", e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCTCATEGORYLEVEL2_DETAILS);
	    }
	    return responseDto;
	}

	
	@Override
	public ResponseDto getProductCategoryLevel2ByName(String productCategoryLevel2Name) {
	    log.info("ProductCategoryLevel2ServiceImpl.getProductCategoryLevel2ByName() invoked");

	    try {
	        List<ProductCategoryLevel2Dto> productCategoryLevel2List = productCategoryLevel2ServiceBL.getProductCategoryLevel2ByName(productCategoryLevel2Name);
	        if (!productCategoryLevel2List.isEmpty()) {
	            return serviceUtil.getServiceResponse(productCategoryLevel2List);
	        } else {
	            return serviceUtil.getErrorServiceResponse(
	                ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCTCATEGORYLEVEL2_BY_NAME);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving ProductCategoryLevel2 by name", e);
	        return serviceUtil.getExceptionServiceResponseByProperties(
	            ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCTCATEGORYLEVEL2_BY_NAME);
	    }
	}
	
	@Override
	public ResponseDto updateProductCategoryLevel2Status(Integer productCategoryLevel2Id, Boolean status) {
		log.info("InvoiceServiceImpl.updateProductCategoryLevel2Status(ProductCategoryLevel2Dto productCategoryLevel2Dto) invoked");
		ResponseDto responseDto = null;
		try {
			ProductCategoryLevel2Dto updatedProductCategoryLevel2StatusDto = productCategoryLevel2ServiceBL.updateProductCategoryLevel2Status(productCategoryLevel2Id, status);
			if (updatedProductCategoryLevel2StatusDto != null) {
				log.info("ProductCategoryLevel2 Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedProductCategoryLevel2StatusDto);
			} else {
				log.info("Unable to update ProductCategoryLevel2 status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCTCATEGORYLEVEL2_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating ProductCategoryLevel2 status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCTCATEGORYLEVEL2_STATUS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getProductCategoryLevel2ById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<ProductCategoryLevel2Dto> productCategoryLevel2DtoList = productCategoryLevel2ServiceBL.getProductCategoryLevel2ById(id);
			if (productCategoryLevel2DtoList != null && !productCategoryLevel2DtoList.isEmpty()) {
				log.info("ProductCategoryLevel2 detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(productCategoryLevel2DtoList);
			} else {
				log.info("Unable to Retrive ProductCategoryLevel2 detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCTCATEGORYLEVEL2_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the branch detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCTCATEGORYLEVEL2_BY_ID);
		}
		return responseDto;
	}
	
	@Override
    public ResponseDto getAllProductCategoryLevel2(String productCategoryLevel2Name) {
        log.info("ProductCategoryLevel2ServiceImpl.getAllProductCategoryLevel2() invoked with ProductCategoryLevel2Name: {}", productCategoryLevel2Name);
        ResponseDto responseDto = null;
        try {
            List<ProductCategoryLevel2Dto> productCategoryLevel2List = productCategoryLevel2ServiceBL.getAllProductCategoryLevel2(productCategoryLevel2Name);
            if (productCategoryLevel2List != null && !productCategoryLevel2List.isEmpty()) {
                log.info("Retrieved all ProductCategoryLevel2 details.");
                responseDto = serviceUtil.getServiceResponse(productCategoryLevel2List);
            } else {
                log.info("Unable to retrieve all ProductCategoryLevel2 details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCTCATEGORYLEVEL2_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all ProductCategoryLevel2 details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCTCATEGORYLEVEL2_DETAILS);
        }
        return responseDto;
    }
}
