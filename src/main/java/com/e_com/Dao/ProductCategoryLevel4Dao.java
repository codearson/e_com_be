package com.e_com.Dao;

import com.e_com.Domain.ProductCategoryLevel4;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductCategoryLevel4Dto;
import java.util.List;
import java.util.Map;

/**
 * Title: ProductCategoryLevel4Dao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date Jan 27, 2025
 * @time 5:07 PM
 * @version 1.0
 **/

public interface ProductCategoryLevel4Dao extends BaseDao<ProductCategoryLevel4> {
	
    ProductCategoryLevel4Dto saveProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto);
    
    ProductCategoryLevel4Dto updateProductCategoryLevel4(ProductCategoryLevel4Dto productCategoryLevel4Dto);
    
    ProductCategoryLevel4Dto checkProductCategoryLevel4Availability(Integer productCategoryLevel4Id);
    
    PaginatedResponseDto getAllPageProductCategoryLevel4(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<ProductCategoryLevel4Dto> getAllBySearchProductCategoryLevel4(String name);
    
} 