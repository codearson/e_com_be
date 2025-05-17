package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: ProductCategoryService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 17, 2025
 * @time 12:10:43 AM
 * @version 1.0
 **/

public interface ProductCategoryService {
	
	ResponseDto saveProductCategory(ProductCategoryDto productCategoryDto);
	
    ResponseDto updateProductCategory(ProductCategoryDto productCategoryDto);
    
    ResponseDto updateProductCategoryStatus(Integer productCategoryId, Boolean status);
    
    ResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    ResponseDto getAllBySearchProductCategory(String name);
    
}
