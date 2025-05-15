package com.e_com.Service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.e_com.Dto.ProductSubCategoryDto;
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
public interface ProductSubCategoryService {
 
	    ResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams);
		
		public ResponseDto save(ProductSubCategoryDto productSubCategoryDto);
		
		ResponseDto updateProductSubCategory(ProductSubCategoryDto productSubCategoryDto);
		
		ResponseDto getProductSubCategoryByName(String productSubCategoryName);
		
		public ResponseDto updateProductSubCategoryStatus(Integer productSubCategoryId, Boolean status);
		
		public ResponseDto getProductSubCategoryById(Integer id);
		
		ResponseDto getAllProductSubCategory(String productSubCategoryName);

}
