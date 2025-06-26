package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel2Dto;

/**
 * Title: ProductSubCategoryDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 8:14:43 PM
 * @version 1.0
 **/

public interface ProductCategoryLevel2Dao {

    PaginatedResponseDto getAllPageProductCategoryLevel2(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
	
    ProductCategoryLevel2Dto save (ProductCategoryLevel2Dto productCategoryLevel2Dto);

    ProductCategoryLevel2Dto update(ProductCategoryLevel2Dto productCategoryLevel2Dto);
	
	List<ProductCategoryLevel2Dto> getProductCategoryLevel2ByName(String productCategoryLevel2Name);
	
	ProductCategoryLevel2Dto checkProductCategoryLevel2Availability(Integer productCategoryLevel2Id);
	
	List<ProductCategoryLevel2Dto> getProductCategoryLevel2ById(Integer id);
	
	List<ProductCategoryLevel2Dto> getAllProductCategoryLevel2(String productCategoryLevel2Name);

}
