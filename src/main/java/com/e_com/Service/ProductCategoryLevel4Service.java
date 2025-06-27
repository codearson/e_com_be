package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.ProductCategoryLevel4Dto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: ProductCategoryLevel4Service.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date Jan 27, 2025
 * @time 5:22 PM
 * @version 1.0
 **/

public interface ProductCategoryLevel4Service {
	
	ResponseDto saveProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto);
	
    ResponseDto updateProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto);
    
    ResponseDto updateProductCategoryLevel4Status(Integer productCategoryLevel4Id, Boolean status);
    
    ResponseDto getAllPageProductCategoryLevel4(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    ResponseDto getAllBySearchProductCategoryLevel4(String name);
    
} 