package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.ProductCategoryLevel1Dto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: ProductCategoryService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 17, 2025
 * @time 12:10:43 AM
 * @version 1.0
 **/

public interface ProductCategoryLevel1Service {
	
	ResponseDto saveProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto);
	
    ResponseDto updateProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto);
    
    ResponseDto updateProductCategoryLevel1Status(Integer productCategoryLevel1Id, Boolean status);
    
    ResponseDto getAllPageProductCategoryLevel1(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    ResponseDto getAllBySearchProductCategoryLevel1(String name);
    
}
