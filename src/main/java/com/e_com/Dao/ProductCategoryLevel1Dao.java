package com.e_com.Dao;

import com.e_com.Domain.ProductCategoryLevel1;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel1Dto;
import java.util.List;
import java.util.Map;

/**
 * Title: ProductServiceDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 17, 2025
 * @time 12:15:56 AM
 * @version 1.0
 **/

public interface ProductCategoryLevel1Dao extends BaseDao<ProductCategoryLevel1> {
	
    ProductCategoryLevel1Dto saveProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto);
    
    ProductCategoryLevel1Dto updateProductCategoryLevel1(ProductCategoryLevel1Dto productCategoryLevel1Dto);
    
    ProductCategoryLevel1Dto checkProductCategoryLevel1Availability(Integer productCategoryLevel1Id);
    
    PaginatedResponseDto getAllPageProductCategoryLevel1(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<ProductCategoryLevel1Dto> getAllBySearchProductCategoryLevel1(String name);
    
}