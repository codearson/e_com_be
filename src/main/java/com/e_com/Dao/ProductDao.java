package com.e_com.Dao;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.e_com.Domain.Product;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ProductDto;

/**
 * Title: ProductDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 16 May 2025
 * @time 18:34:44
 * @version 1.0
 **/

public interface ProductDao extends BaseDao<Product> {
	
    ProductDto saveProduct(ProductDto productDto);
    
    ProductDto updateProduct(ProductDto productDto);
    
    ProductDto checkProductAvailability(Integer productId);
    
    PaginatedResponseDto getAllPageProduct(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    PaginatedResponseDto getAllPageProductBySearch(int pageNumber, int pageSize, Boolean status, String title, String description, Map<String, String> searchParameters);
    
    PaginatedResponseDto getAllPageFilter(int pageNumber, int pageSize, Boolean status, String category, String size, String brandName, String conditionType, String color, Map<String, String> searchParameters);
    
    List<ProductDto> getAllBySearchProduct(String categoryName, String brandName, String conditionType, String type, String title);
    
    PaginatedResponseDto getAllPageSortByPrice(int pageNumber, int pageSize,Boolean status, Boolean asc);
 
    void updateProductIsActiveBasedOnQuantity(Integer productId);

    List<ProductDto> getProductsByCategoryAndDescendants(Long categoryId);

}