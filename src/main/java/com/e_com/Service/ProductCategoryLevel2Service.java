package com.e_com.Service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.e_com.Dto.ProductCategoryLevel2Dto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: ProductSubCategoryService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 4:32:39 PM
 * @version 1.0
 **/
@Service
public interface ProductCategoryLevel2Service {
 
	    ResponseDto getAllPageProductCategoryLevel2(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
		
		public ResponseDto save(ProductCategoryLevel2Dto productCategoryLevel2Dto);
		
		ResponseDto updateProductCategoryLevel2(ProductCategoryLevel2Dto productCategoryLevel2Dto);
		
		ResponseDto getProductCategoryLevel2ByName(String productCategoryLevel2Name);
		
		public ResponseDto updateProductCategoryLevel2Status(Integer productCategoryLevel2Id, Boolean status);
		
		public ResponseDto getProductCategoryLevel2ById(Integer id);
		
		ResponseDto getAllProductCategoryLevel2(String productCategoryLevel2Name);

}
