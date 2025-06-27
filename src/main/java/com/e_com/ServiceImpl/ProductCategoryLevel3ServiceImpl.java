package com.e_com.ServiceImpl;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ProductCategoryLevel3Dto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.ProductCategoryLevel3Service;
import com.e_com.Service.BL.ProductCategoryLevel3ServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductCategoryLevel3ServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 27, 2025
 * @time 5:25:54 PM
 * @version 1.0
 **/

@Slf4j
@Component
public class ProductCategoryLevel3ServiceImpl implements ProductCategoryLevel3Service {

	@Autowired
	private ServiceUtil serviceUtil;

	@Autowired
	private ProductCategoryLevel3ServiceBL productCategoryLevel3ServiceBL;

	@Override
	public ResponseDto getAllPageProductCategoryLevel3(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
		log.info("ProductCategoryLevel3ServiceImpl.getAllPageProductCategoryLevel3() invoked with pageNumber: {}, pageSize: {}, status: {}", pageNumber, pageSize, status);
		ResponseDto responseDto = null;
		try {
			if (pageNumber < 1 || pageSize < 0) {
				log.info("Invalid pagination parameters provided.");
				return serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAGINATED_PRODUCTCATEGORY_LEVEL3_DETAILS);
			}
			PaginatedResponseDto paginatedResponseDto = productCategoryLevel3ServiceBL.getAllPageProductCategoryLevel3(pageNumber, pageSize, status, searchParameters);
			if (paginatedResponseDto != null) {
				log.info("Retrieved paginated ProductCategoryLevel3 details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve paginated ProductCategoryLevel3 details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PAGINATED_PRODUCTCATEGORY_LEVEL3_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving paginated ProductCategoryLevel3 details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PAGINATED_PRODUCTCATEGORY_LEVEL3_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(ProductCategoryLevel3Dto productCategoryLevel3Dto) {
		log.info("ProductCategoryLevel3ServiceImpl.save invoked");
		ResponseDto responseDto = null;
		try {
			ProductCategoryLevel3Dto saveProductCategoryLevel3Dto = productCategoryLevel3ServiceBL.save(productCategoryLevel3Dto);
			if (saveProductCategoryLevel3Dto != null) {
				log.info("ProductCategoryLevel3 Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveProductCategoryLevel3Dto);
			} else {
				log.info("Unable to save ProductCategoryLevel3 details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCTCATEGORY_LEVEL3_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving ProductCategoryLevel3 details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCTCATEGORY_LEVEL3_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateProductCategoryLevel3(ProductCategoryLevel3Dto productCategoryLevel3Dto) {
		log.info("ProductCategoryLevel3ServiceImpl.updateProductCategoryLevel3() invoked");
		ResponseDto responseDto = null;
		try {
			ProductCategoryLevel3Dto updatedProductCategoryLevel3 = productCategoryLevel3ServiceBL.updateProductCategoryLevel3(productCategoryLevel3Dto);
			if (updatedProductCategoryLevel3 != null) {
				log.info("ProductCategoryLevel3 details updated successfully.");
				responseDto = serviceUtil.getServiceResponse(updatedProductCategoryLevel3);
			} else {
				log.info("Failed to update ProductCategoryLevel3 details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCTCATEGORY_LEVEL3_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurred while updating ProductCategoryLevel3 details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCTCATEGORY_LEVEL3_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getProductCategoryLevel3ByName(String productCategoryLevel3Name) {
		log.info("ProductCategoryLevel3ServiceImpl.getProductCategoryLevel3ByName() invoked");
		try {
			List<ProductCategoryLevel3Dto> productCategoryLevel3List = productCategoryLevel3ServiceBL.getProductCategoryLevel3ByName(productCategoryLevel3Name);
			if (!productCategoryLevel3List.isEmpty()) {
				return serviceUtil.getServiceResponse(productCategoryLevel3List);
			} else {
				return serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCTCATEGORY_LEVEL3_BY_NAME);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving ProductCategoryLevel3 by name", e);
			return serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCTCATEGORY_LEVEL3_BY_NAME);
		}
	}

	@Override
	public ResponseDto updateProductCategoryLevel3Status(Integer productCategoryLevel3Id, Boolean status) {
		log.info("ProductCategoryLevel3ServiceImpl.updateProductCategoryLevel3Status() invoked");
		ResponseDto responseDto = null;
		try {
			ProductCategoryLevel3Dto updatedProductCategoryLevel3StatusDto = productCategoryLevel3ServiceBL.updateProductCategoryLevel3Status(productCategoryLevel3Id, status);
			if (updatedProductCategoryLevel3StatusDto != null) {
				log.info("ProductCategoryLevel3 Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedProductCategoryLevel3StatusDto);
			} else {
				log.info("Unable to update ProductCategoryLevel3 status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCTCATEGORY_LEVEL3_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating ProductCategoryLevel3 status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCTCATEGORY_LEVEL3_STATUS);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto getProductCategoryLevel3ById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<ProductCategoryLevel3Dto> productCategoryLevel3DtoList = productCategoryLevel3ServiceBL.getProductCategoryLevel3ById(id);
			if (productCategoryLevel3DtoList != null && !productCategoryLevel3DtoList.isEmpty()) {
				log.info("ProductCategoryLevel3 detail retrieved successfully.");
				responseDto = serviceUtil.getServiceResponse(productCategoryLevel3DtoList);
			} else {
				log.info("Unable to retrieve ProductCategoryLevel3 detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCTCATEGORY_LEVEL3_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving the ProductCategoryLevel3 detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCTCATEGORY_LEVEL3_BY_ID);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAllProductCategoryLevel3(String productCategoryLevel3Name) {
		log.info("ProductCategoryLevel3ServiceImpl.getAllProductCategoryLevel3() invoked with ProductCategoryLevel3Name: {}", productCategoryLevel3Name);
		ResponseDto responseDto = null;
		try {
			List<ProductCategoryLevel3Dto> productCategoryLevel3List = productCategoryLevel3ServiceBL.getAllProductCategoryLevel3(productCategoryLevel3Name);
			if (productCategoryLevel3List != null && !productCategoryLevel3List.isEmpty()) {
				log.info("Retrieved all ProductCategoryLevel3 details.");
				responseDto = serviceUtil.getServiceResponse(productCategoryLevel3List);
			} else {
				log.info("Unable to retrieve all ProductCategoryLevel3 details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCTCATEGORY_LEVEL3_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving all ProductCategoryLevel3 details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCTCATEGORY_LEVEL3_DETAILS);
		}
		return responseDto;
	}
}
