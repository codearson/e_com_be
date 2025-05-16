package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductSubCategoryDto;

/**
 * Title: ProductSubCategoryDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 8:14:43 PM
 * @version 1.0
 **/

public interface ProductSubCategoryDao {

    PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams);
	
    ProductSubCategoryDto save (ProductSubCategoryDto productSubCategoryDto);

    ProductSubCategoryDto update(ProductSubCategoryDto productSubCategoryDto);
	
	List<ProductSubCategoryDto> getProductSubCategoryByName(String productSubCategoryName);
	
	ProductSubCategoryDto checkProductSubCategoryAvailability(Integer productSubCategoryId);
	
	List<ProductSubCategoryDto> getProductSubCategoryById(Integer id);
	
	List<ProductSubCategoryDto> getAllProductSubCategory(String productSubCategoryName);

}
