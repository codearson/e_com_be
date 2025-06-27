package com.e_com.Service;

import com.e_com.Dto.ProductCategoryLevel3Dto;
import com.e_com.Dto.ResponseDto;
import java.util.Map;

/**
 * Title: ProductCategoryLevel3Service.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 27, 2025
 * @time 5:20:31 PM
 * @version 1.0
 **/

public interface ProductCategoryLevel3Service {
	
	public ResponseDto save(ProductCategoryLevel3Dto productCategoryLevel3Dto);
	
	ResponseDto getAllPageProductCategoryLevel3(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
	
	ResponseDto updateProductCategoryLevel3(ProductCategoryLevel3Dto productCategoryLevel3Dto);
	
	ResponseDto getProductCategoryLevel3ByName(String productCategoryLevel3Name);
	
	ResponseDto updateProductCategoryLevel3Status(Integer productCategoryLevel3Id, Boolean status);
	
	ResponseDto getProductCategoryLevel3ById(Integer id);
	
	ResponseDto getAllProductCategoryLevel3(String productCategoryLevel3Name);
}
