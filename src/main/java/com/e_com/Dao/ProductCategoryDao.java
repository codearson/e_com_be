package com.e_com.Dao;

import com.e_com.Domain.ProductCategory;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryDto;
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

public interface ProductCategoryDao extends BaseDao<ProductCategory> {
	
    ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto);
    
    ProductCategoryDto updateProductCategory(ProductCategoryDto productCategoryDto);
    
    ProductCategoryDto checkProductCategoryAvailability(Integer productCategoryId);
    
    PaginatedResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<ProductCategoryDto> getAllBySearchProductCategory(String name);
    
}